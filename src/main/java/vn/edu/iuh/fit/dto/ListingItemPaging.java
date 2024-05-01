package vn.edu.iuh.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.models.Device;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ListingItemPaging {
    private int pageNo;
    private int sizeNo;
    private List<MyDeviceItem> devices;

}
