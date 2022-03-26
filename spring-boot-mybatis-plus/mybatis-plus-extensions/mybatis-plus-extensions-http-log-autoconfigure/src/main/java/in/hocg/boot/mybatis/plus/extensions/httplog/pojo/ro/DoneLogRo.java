package in.hocg.boot.mybatis.plus.extensions.httplog.pojo.ro;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/3/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class DoneLogRo implements Serializable {
    private Long id;
    private String responseHeaders;
    private String responseBody;
    private String status;
    private LocalDateTime doneAt;
    private String failReason;
}
