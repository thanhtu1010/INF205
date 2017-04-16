/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunt.model;

import entity.Students;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Iris Nguyen
 */
public class StudentDAO {
    public static List<Students> layDanhSachSV(String ten){
        List<Students> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql="from Students";
        if(ten.length()>0){
            sql += " where name like '%"+ten+"%'";
        }
        Query query = session.createQuery(sql);
        list = query.list();
        return list;
    }    
    public static List<Students> LayDanhSachSinhVien(){
        List<Students> dsKhachHang=null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql="from Students";
        Query query = session.createQuery(hql);
        dsKhachHang = query.list();
        return dsKhachHang;
    }
    public static Students layThongTinSinhVien(int maSV){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Students kh = (Students)session.get(Students.class, maSV);
        session.close();
        return kh;
    }
    public static boolean themSinhVien(Students sv){
        if(StudentDAO.layThongTinSinhVien(sv.getMasv())!=null)
            return false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.getTransaction().begin();
            session.save(sv);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
            return false;
        }
//        }finally{
//            session.close();
//        }
    }
    public static boolean updateThongTinSinhVien(Students sv){
        if(StudentDAO.layThongTinSinhVien(sv.getMasv())==null)
            return false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.getTransaction().begin();
            session.update(sv);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
            return false;
        }
    }
    public static boolean deleteSinhVien(int maSV){
        Students sv = StudentDAO.layThongTinSinhVien(maSV);
        if(sv==null)
            return false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.getTransaction().begin();
            session.delete(sv);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
            return false;
        }
    }
}
