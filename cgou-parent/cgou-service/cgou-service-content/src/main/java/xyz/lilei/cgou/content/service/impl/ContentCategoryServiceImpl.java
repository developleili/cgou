package xyz.lilei.cgou.content.service.impl;
import xyz.lilei.cgou.content.dao.ContentCategoryMapper;
import xyz.lilei.cgou.content.pojo.ContentCategory;
import xyz.lilei.cgou.content.service.ContentCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:admin
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;


    /**
     * ContentCategory����+��ҳ��ѯ
     * @param contentCategory ��ѯ����
     * @param page ҳ��
     * @param size ҳ��С
     * @return ��ҳ���
     */
    @Override
    public PageInfo<ContentCategory> findPage(ContentCategory contentCategory, int page, int size){
        //��ҳ
        PageHelper.startPage(page,size);
        //������������
        Example example = createExample(contentCategory);
        //ִ������
        return new PageInfo<ContentCategory>(contentCategoryMapper.selectByExample(example));
    }

    /**
     * ContentCategory��ҳ��ѯ
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<ContentCategory> findPage(int page, int size){
        //��̬��ҳ
        PageHelper.startPage(page,size);
        //��ҳ��ѯ
        return new PageInfo<ContentCategory>(contentCategoryMapper.selectAll());
    }

    /**
     * ContentCategory������ѯ
     * @param contentCategory
     * @return
     */
    @Override
    public List<ContentCategory> findList(ContentCategory contentCategory){
        //������ѯ����
        Example example = createExample(contentCategory);
        //���ݹ�����������ѯ����
        return contentCategoryMapper.selectByExample(example);
    }


    /**
     * ContentCategory������ѯ����
     * @param contentCategory
     * @return
     */
    public Example createExample(ContentCategory contentCategory){
        Example example=new Example(ContentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if(contentCategory!=null){
            // ��ĿID
            if(!StringUtils.isEmpty(contentCategory.getId())){
                    criteria.andEqualTo("id",contentCategory.getId());
            }
            // ��������
            if(!StringUtils.isEmpty(contentCategory.getName())){
                    criteria.andLike("name","%"+contentCategory.getName()+"%");
            }
        }
        return example;
    }

    /**
     * ɾ��
     * @param id
     */
    @Override
    public void delete(Long id){
        contentCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * �޸�ContentCategory
     * @param contentCategory
     */
    @Override
    public void update(ContentCategory contentCategory){
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
    }

    /**
     * ����ContentCategory
     * @param contentCategory
     */
    @Override
    public void add(ContentCategory contentCategory){
        contentCategoryMapper.insert(contentCategory);
    }

    /**
     * ����ID��ѯContentCategory
     * @param id
     * @return
     */
    @Override
    public ContentCategory findById(Long id){
        return  contentCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * ��ѯContentCategoryȫ������
     * @return
     */
    @Override
    public List<ContentCategory> findAll() {
        return contentCategoryMapper.selectAll();
    }
}
