package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 公寓考勤数据
 *
 
 *  
 */
public class GykqEntity implements Serializable {
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
        
            //日期
                    private Date time;
        
            //考勤状态
                    private String zt;
        
    
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
         * 设置：日期
         */
        public void setTime(Date time) {
            this.time = time;
        }

        /**
         * 获取：日期
         */
        public Date getTime() {
            return time;
        }
            /**
         * 设置：考勤状态
         */
        public void setZt(String zt) {
            this.zt = zt;
        }

        /**
         * 获取：考勤状态
         */
        public String getZt() {
            return zt;
        }
    }
