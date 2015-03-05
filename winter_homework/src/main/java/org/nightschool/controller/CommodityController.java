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

//import com.sun.jersey.media.multipart.FormDataParam;
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
        fileName= "imgs/" + fileDetail.getFileName();
        writeToFile(uploadedInputStream, uploadedFileLocation);
        String output = "File uploaded to : " + uploadedFileLocation;
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/getCommodities")
    public List<Commodity> getCommodities() throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CommodityMapper mapper = session.getMapper(CommodityMapper.class);
        List<Commodity> commodities = mapper.getCommodities();
        System.out.println(commodities.size()+"*************");
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
    @Path("addCommodity")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCommodity(Commodity c) throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CommodityMapper mapper = session.getMapper(CommodityMapper.class);
        mapper.insert(c);
        session.commit();
        fileName=null;
    }
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        while ((read = uploadedInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }



}
