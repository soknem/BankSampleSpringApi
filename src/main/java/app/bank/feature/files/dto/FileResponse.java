package app.bank.feature.files.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
public record FileResponse(String name,String fullUrl,String downloadUrl,String type,float size) {
}
