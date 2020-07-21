package xyz.lilei.cgou.content.service;
import xyz.lilei.cgou.content.pojo.Content;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:admin
 * @Description:Contentҵ���ӿ�
 * @Date 2019/6/14 0:16
 *****/
public interface ContentService {

    /***
     * Content��������ҳ��ѯ
     * @param content
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(Content content, int page, int size);

    /***
     * Content��ҳ��ѯ
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(int page, int size);

    /***
     * Content��������������
     * @param content
     * @return
     */
    List<Content> findList(Content content);

    /***
     * ɾ��Content
     * @param id
     */
    void delete(Long id);

    /***
     * �޸�Content����
     * @param content
     */
    void update(Content content);

    /***
     * ����Content
     * @param content
     */
    void add(Content content);

    /**
     * ����ID��ѯContent
     * @param id
     * @return
     */
     Content findById(Long id);

    /***
     * ��ѯ����Content
     * @return
     */
    List<Content> findAll();

    List<Content> findByCategoryId(Long categoryId);
}
