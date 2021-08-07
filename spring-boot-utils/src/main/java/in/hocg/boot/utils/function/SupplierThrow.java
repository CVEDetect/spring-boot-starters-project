package in.hocg.boot.utils.function;

import java.util.function.Supplier;

/**
 * Created by hocgin on 2021/8/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FunctionalInterface
public interface SupplierThrow<T> {
    T get() throws Exception;
}
