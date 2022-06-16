package in.hocg.boot.mybatis.plus.extensions.tpl.autoconfiguration;

import in.hocg.boot.mybatis.plus.extensions.context.constants.MyBatisPlusExtensionsConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2020/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Setter
@ConfigurationProperties(TplMybatisPlusExtProperties.PREFIX)
public class TplMybatisPlusExtProperties {
    public static final String PREFIX = MyBatisPlusExtensionsConstants.PROPERTIES_PREFIX + ".tpl";

}
