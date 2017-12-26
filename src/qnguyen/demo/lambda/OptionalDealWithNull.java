package qnguyen.demo.lambda;

import java.math.BigDecimal;
import java.util.Optional;

public class OptionalDealWithNull {

    private int vatConfigurationDto;
    private int vatCacheService;

    // imperative way
    public BigDecimal getPriceValue(BigDecimal priceNet, BigDecimal priceGross, Long itemId) {
        BigDecimal result;
        if (VatUtils.getDisplayGross(vatConfigurationDto) && priceGross != null) {
            result =  priceGross;
        } else if (VatUtils.getDisplayGross(vatConfigurationDto) && priceGross == null) {
            result = VatUtils.convertPriceNetToGrossUsingConfiguration(priceNet, itemId, this.vatConfigurationDto,
                                                                       this.vatCacheService);
        } else {
            result = priceNet;
        }
        return result;
    }


    // in more declarative way
    public BigDecimal getPriceValueRefactor(BigDecimal priceNet, BigDecimal priceGross, Long itemId) {
        if ((VatUtils.getDisplayGross(vatConfigurationDto))) {
            return Optional.ofNullable(priceGross)
                           .orElse(VatUtils.convertPriceNetToGrossUsingConfiguration(priceNet, itemId,
                                                                                     this.vatConfigurationDto, this.vatCacheService));
        }
        return priceNet;
    }


    private static class VatUtils {

        public static boolean getDisplayGross(int vatConfigurationDto) {
            return false;
        }

        public static BigDecimal convertPriceNetToGrossUsingConfiguration(BigDecimal priceNet, Long itemId, int vatConfigurationDto, int vatCacheService) {
            return BigDecimal.ONE; 
        }
    }
}
