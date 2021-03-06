package xyz.lilei.cgou.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import xyz.lilei.cgou.goods.dao.BrandMapper;
import xyz.lilei.cgou.goods.pojo.Brand;
import xyz.lilei.cgou.goods.service.BrandService;

import java.util.List;

/**
 * @ClassName BrandServiceImpl
 * @Description TODO
 * @Author lilei
 * @Date 02/07/2020 00:46
 * @Version 1.0
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 全部数据
     * @return
     */
    public List<Brand> findAll(){
        return brandMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id){
        return  brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param brand
     */
    @Override
    public void add(Brand brand){
        brandMapper.insert(brand);
    }

    /**
     * 修改
     * @param brand
     */
    @Override
    public void update(Brand brand){
        brandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand){
        //构建查询条件
        Example example = createExample(brand);
        //根据构建的条件查询数据
        return brandMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param brand
     * @return
     */
    public Example createExample(Brand brand){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(brand!=null){
            // 品牌名称
            if(!StringUtils.isEmpty(brand.getName())){
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            // 品牌图片地址
            if(!StringUtils.isEmpty(brand.getImage())){
                criteria.andLike("image","%"+brand.getImage()+"%");
            }
            // 品牌的首字母
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andLike("letter","%"+brand.getLetter()+"%");
            }
            // 品牌id
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("id",brand.getId());
            }
            // 排序
            if(brand.getSeq()!=null){
                criteria.andEqualTo("seq",brand.getSeq());
            }
        }
        return example;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Brand>(brandMapper.selectAll());
    }

    /**
     * 条件+分页查询
     * @param brand 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(brand);
        //执行搜索
        return new PageInfo<Brand>(brandMapper.selectByExample(example));
    }

    /***
     * 根据分类ID查询品牌集合
     * @param categoryId:分类ID
     * @return
     */
    @Override
    public List<Brand> findByCategory(Integer categoryId) {
        //1.查询当前分类所对应的所有品牌信息
        //2.根据品牌ID查询对应的品牌集合

        //自己创建DAO实现查询
        return brandMapper.findByCategoryId(categoryId);
    }
}
