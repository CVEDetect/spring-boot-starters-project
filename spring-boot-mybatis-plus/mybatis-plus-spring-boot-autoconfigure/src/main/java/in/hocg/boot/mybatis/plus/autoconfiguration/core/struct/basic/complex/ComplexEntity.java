package in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.complex;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.ColumnConstants;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.common.CommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/1/5.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class ComplexEntity<T extends ComplexEntity<?>> extends CommonEntity<T> {
    @TableField(ColumnConstants.TENANT_ID)
    private Long tenantId;

    @TableLogic
    @TableField(value = ColumnConstants.DELETED_AT)
    private LocalDateTime deletedAt;
    @TableField(value = ColumnConstants.DELETER)
    private Long deleter;
}
