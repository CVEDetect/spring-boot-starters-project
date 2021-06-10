package in.hocg.boot.changelog.autoconfiguration.compare;

import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.PropertyNamer;
import in.hocg.boot.utils.lambda.SFunction;
import in.hocg.boot.utils.lambda.SerializedLambda;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2020/4/10.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class EntityCompare {

    /**
     * 比较
     *
     * @param o
     * @param n
     * @param ignoreNull
     * @param ignoreFunc
     * @return
     */
    public static <T> List<FieldChangeDto> diffUseLambda(@NonNull T o, @NonNull T n, boolean ignoreNull, SFunction<T, ?>... ignoreFunc) {
        final List<String> ignoreFieldNames = Arrays.stream(ignoreFunc).map(func -> PropertyNamer.methodToProperty(SerializedLambda.resolve(func).getImplMethodName()))
            .collect(Collectors.toList());
        return EntityCompare.diff(o, n, ignoreNull, ignoreFieldNames);
    }


    /**
     * 对比记录变更的字段
     *
     * @param o                旧的值
     * @param n                新的值
     * @param ignoreNull       是否忽略 NULL
     * @param ignoreFieldNames 需忽略的字段名
     * @return
     */
    @SneakyThrows
    public static <T> List<FieldChangeDto> diff(@NonNull T o, @NonNull T n, boolean ignoreNull, @NonNull List<String> ignoreFieldNames) {
        final List<FieldChangeDto> result = Lists.newArrayList();
        final Class<?> nClass = n.getClass();
        final Class<?> oClass = o.getClass();
        final Map<String, Field> oFieldMaps = ReflectUtil.getFieldMap(oClass);
        final List<Field> nFields = Lists.newArrayList(ReflectUtil.getFields(nClass));
        for (Field nField : nFields) {
            final String nFieldName = nField.getName();

            // 如果需要忽略字段名
            if (ignoreFieldNames.contains(nFieldName)) {
                continue;
            }

            final Object nFieldValue = ReflectUtil.getFieldValue(n, nField);
            // 如果需要忽略 NULL 值
            if (ignoreNull && Objects.isNull(nFieldValue)) {
                continue;
            }
            final Field oField = oFieldMaps.get(nFieldName);
            final Object oFieldValue = ReflectUtil.getFieldValue(o, oField);
            final String after = String.valueOf(nFieldValue);
            final String before = String.valueOf(oFieldValue);

            // 如果两个值一样
            if (LangUtils.equals(after, before)) {
                continue;
            }

            result.add(new FieldChangeDto()
                .setFieldRemark(getFieldRemark(nField))
                .setFieldName(nFieldName)
                .setChangeRemark(String.format("%s: %s -> %s", nFieldName, before, after))
                .setAfterValue(after)
                .setBeforeValue(before));
        }
        return result;
    }

    /**
     * 获取字段备注
     *
     * @param field
     * @return
     */
    private static String getFieldRemark(Field field) {
        final boolean hasApiModeProperty = field.isAnnotationPresent(ApiModelProperty.class);
        if (hasApiModeProperty) {
            final ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            return annotation.value();
        }
        return String.format("%s属性", field.getName());
    }
}
