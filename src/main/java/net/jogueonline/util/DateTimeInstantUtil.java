package net.jogueonline.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class DateTimeInstantUtil {

    // Get current local date time br
    public static Instant getDataTimeNowBr(){
        Instant nowUtc = Instant.now();
        ZoneId brazilSaoPaulo = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime nowBrazil = ZonedDateTime.ofInstant(nowUtc,brazilSaoPaulo);
        return nowBrazil.toInstant();
    }

}
