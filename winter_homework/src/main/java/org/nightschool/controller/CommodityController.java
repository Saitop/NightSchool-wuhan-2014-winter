package org.nightschool.controller;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.ibatis.session.SqlSession;
import org.nightschool.mapper.CommodityMapper;
import org.nightschool.model.Commodity;
import org.nightschool.mybatis.MyBatisUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2015/2/27.
 */

@Path("/commodity")
@Produces(MediaType.APPLICATION_JSON)
public class CommodityController {
    public String fileName;
    @GET
    @Path("upload/fileName")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFileName(){
        return fileName;
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        String uploadedFileLocation = "./src/main/resources/webapp/html/imgs/" + fileDetail.getFileName();
        writeToFile(uploadedInputStream, uploadedFileLocation);
        fileName= "imgs/" + fileDetail.getFileName();
        String output = "File uploaded to : " + uploadedFileLocation;
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/getCommodities")
    public List<Commodity> getCommodities() throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CommodityMapper mapper = session.getMapper(CommodityMapper.class);
        List<Commodity> commodities = mapper.getCommodities();
        return commodities;
    }

    @POST
    @Path("getCommodityById")
    public Commodity getCommodityById(int id) throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CommodityMapper mapper = session.getMapper(CommodityMapper.class);
        Commodity commodity = mapper.getById(id);
       return commodity;
    }

    @POST
    @Path("getMutliCommodity")
    public List<Commodity> getMutliCommodity(int[] ids) throws IOException {
        if(ids.length==0)
            return null;
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CommodityMapper mapper = session.getMapper(CommodityMapper.class);
        List<Commodity> commodities = mapper.getByIdList(ids);
        return  commodities;
    }


    @POST
    @Path("addCommodity")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCommodity(Commodity c) throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CommodityMapper mapper = session.getMapper(CommodityMapper.class);
        mapper.insert(c);
        session.commit();
        fileName=null;
    }
    @POST
    @Path("delMutliCommodity")
    public List<Commodity> delMutliCommodity(int[] ids) throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CommodityMapper mapper = session.getMapper(CommodityMapper.class);
       mapper.deleteByIdList(ids);
        session.commit();
        List<Commodity> commodities = mapper.getByIdList(ids);
        return  commodities;

    }

    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        File file = new File(uploadedFileLocation);
        OutputStream out = new FileOutputStream(file);
        while ((read = uploadedInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }
}
