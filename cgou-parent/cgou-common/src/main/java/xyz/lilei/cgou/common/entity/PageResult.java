package xyz.lilei.cgou.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description TODO
 * @Author lilei
 * @Date 01/07/2020 23:57
 * @Version 1.0
 **/
@Data
public class PageResult<T> {
    private Long total;//总记录数
    private List<T> rows;//记录
}
