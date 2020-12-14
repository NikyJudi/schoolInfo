package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 图书馆刷卡记录
 *
 
 *  
 */
public class TsgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Long id;
        
            //学生
                    private Long sysUser;

            private  SysUserEntity  sysUserEntity;

            public SysUserEntity getSysUserEntity() {
                return sysUserEntity;
            }

            public void setSysUserEntity(SysUserEntity sysUserEntity) {
                this.sysUserEntity = sysUserEntity;
            }
        
            //借阅书籍名称
                    private String name;
        
            //借出时间
                    private Date time;
        
            //归还时间
                    private Date ghtime;
        
    
            /**
         * 设置：
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * 获取：
         */
        public Long getId() {
            return id;
        }
            /**
         * 设置：学生
         */
        public void setSysUser(Long sysUser) {
            this.sysUser = sysUser;
        }

        /**
         * 获取：学生
         */
        public Long getSysUser() {
            return sysUser;
        }
            /**
         * 设置：借阅书籍名称
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取：借阅书籍名称
         */
        public String getName() {
            return name;
        }
            /**
         * 设置：借出时间
         */
        public void setTime(Date time) {
            this.time = time;
        }

        /**
         * 获取：借出时间
         */
        public Date getTime() {
            return time;
        }
            /**
         * 设置：归还时间
         */
        public void setGhtime(Date ghtime) {
            this.ghtime = ghtime;
        }

        /**
         * 获取：归还时间
         */
        public Date getGhtime() {
            return ghtime;
        }
    }
