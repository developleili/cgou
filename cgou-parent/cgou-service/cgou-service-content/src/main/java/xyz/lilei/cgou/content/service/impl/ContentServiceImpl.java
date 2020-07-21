package xyz.lilei.cgou.content.service.impl;
import xyz.lilei.cgou.content.dao.ContentMapper;
import xyz.lilei.cgou.content.pojo.Content;
import xyz.lilei.cgou.content.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:admin
 * @Description:Contentҵ���ӿ�ʵ����
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;


    /**
     * Content����+��ҳ��ѯ
     * @param content ��ѯ����
     * @param page ҳ��
     * @param size ҳ��С
     * @return ��ҳ���
     */
    @Override
    public PageInfo<Content> findPage(Content content, int page, int size){
        //��ҳ
        PageHelper.startPage(page,size);
        //������������
        Example example = createExample(content);
        //ִ������
        return new PageInfo<Content>(contentMapper.selectByExample(example));
    }

    /**
     * Content��ҳ��ѯ
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Content> findPage(int page, int size){
        //��̬��ҳ
        PageHelper.startPage(page,size);
        //��ҳ��ѯ
        return new PageInfo<Content>(contentMapper.selectAll());
    }

    /**
     * Content������ѯ
     * @param content
     * @return
     */
    @Override
    public List<Content> findList(Content content){
        //������ѯ����
        Example example = createExample(content);
        //���ݹ�����������ѯ����
        return contentMapper.selectByExample(example);
    }


    /**
     * Content������ѯ����
     * @param content
     * @return
     */
    public Example createExample(Content content){
        Example example=new Example(Content.class);
        Example.Criteria criteria = example.createCriteria();
        if(content!=null){
            // 
            if(!StringUtils.isEmpty(content.getId())){
                    criteria.andEqualTo("id",content.getId());
            }
            // ������ĿID
            if(!StringUtils.isEmpty(content.getCategoryId())){
                    criteria.andEqualTo("categoryId",content.getCategoryId());
            }
            // ���ݱ���
            if(!StringUtils.isEmpty(content.getTitle())){
                    criteria.andLike("title","%"+content.getTitle()+"%");
            }
            // ����
            if(!StringUtils.isEmpty(content.getUrl())){
                    criteria.andEqualTo("url",content.getUrl());
            }
            // ͼƬ����·��
            if(!StringUtils.isEmpty(content.getPic())){
                    criteria.andEqualTo("pic",content.getPic());
            }
            // ״̬,0��Ч��1��Ч
            if(!StringUtils.isEmpty(content.getStatus())){
                    criteria.andEqualTo("status",content.getStatus());
            }
            // ����
            if(!StringUtils.isEmpty(content.getSortOrder())){
                    criteria.andEqualTo("sortOrder",content.getSortOrder());
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
        contentMapper.deleteByPrimaryKey(id);
    }

    /**
     * �޸�Content
     * @param content
     */
    @Override
    public void update(Content content){
        contentMapper.updateByPrimaryKey(content);
    }

    /**
     * ����Content
     * @param content
     */
    @Override
    public void add(Content content){
        contentMapper.insert(content);
    }

    /**
     * ����ID��ѯContent
     * @param id
     * @return
     */
    @Override
    public Content findById(Long id){
        return  contentMapper.selectByPrimaryKey(id);
    }

    /**
     * ��ѯContentȫ������
     * @return
     */
    @Override
    public List<Content> findAll() {
        return contentMapper.selectAll();
    }

    @Override
    public List<Content> findByCategoryId(Long categoryId) {
        Example example = new Example(Content.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId", categoryId);
        return contentMapper.selectByExample(example);
    }
}
