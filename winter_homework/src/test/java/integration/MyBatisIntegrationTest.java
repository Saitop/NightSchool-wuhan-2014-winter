package integration;

import org.junit.Test;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.model.Disk;
import org.nightschool.mybatis.MyBatisUtil;

import java.util.List;

/**
 * Created by Jane on 2015/1/10.
 */
public class MyBatisIntegrationTest {
    @Test
    public void should_get_disks() throws Exception {
        DiskMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(DiskMapper.class);
        List<Disk> disks=mapper.getDisks();
//        assertThat(disks.size(),is(84));
    }

//    @Test
//    public void should_get_data_after_insert_disk() throws Exception {
//        DiskMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(DiskMapper.class);
//        List<Disk> disks = mapper.getDisks();
//        assertThat(disks.size(), is(58));
//        mapper.insert(new Disk("小清新", "url", "desc", 100, 10));
//        disks = mapper.getDisks();
//        assertThat(disks.size(), is(59));
//    }
}
