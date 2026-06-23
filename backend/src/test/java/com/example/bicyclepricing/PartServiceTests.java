package com.example.bicyclepricing;

import com.example.bicyclepricing.dto.PartDto;
import com.example.bicyclepricing.dto.UpdatePartPriceRequest;
import com.example.bicyclepricing.entity.Part;
import com.example.bicyclepricing.entity.PriceHistory;
import com.example.bicyclepricing.exception.ResourceNotFoundException;
import com.example.bicyclepricing.repository.PartRepository;
import com.example.bicyclepricing.repository.PriceHistoryRepository;
import com.example.bicyclepricing.service.PartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PartServiceTests {

    private PartRepository partRepository;
    private PriceHistoryRepository priceHistoryRepository;
    private PartService partService;

    @BeforeEach
    void setUp() {
        partRepository = Mockito.mock(PartRepository.class);
        priceHistoryRepository = Mockito.mock(PriceHistoryRepository.class);
        partService = new PartService(partRepository, priceHistoryRepository);
    }

    @Test
    void updatePrice_shouldStoreHistory() {
        Part part = new Part("Brake", "Safety", new BigDecimal("50.00"));
        part.setId(1L);

        when(partRepository.findById(1L)).thenReturn(Optional.of(part));
        when(partRepository.save(any(Part.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(priceHistoryRepository.save(any(PriceHistory.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdatePartPriceRequest request = new UpdatePartPriceRequest(new BigDecimal("55.00"));
        PartDto updated = partService.updatePrice(1L, request);

        assertThat(updated.getPrice()).isEqualByComparingTo(new BigDecimal("55.00"));
        ArgumentCaptor<PriceHistory> captor = ArgumentCaptor.forClass(PriceHistory.class);
        verify(priceHistoryRepository, times(1)).save(captor.capture());
        assertThat(captor.getValue().getOldPrice()).isEqualByComparingTo(new BigDecimal("50.00"));
        assertThat(captor.getValue().getNewPrice()).isEqualByComparingTo(new BigDecimal("55.00"));
    }

    @Test
    void findById_whenMissing_shouldThrow() {
        when(partRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> partService.findById(99L));
    }
}
