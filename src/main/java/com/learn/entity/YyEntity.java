package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 医院就医数据记录
 *
 
 *  
 */
public class YyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Integer id;
        
            //学生
                    private Long sysUser;

            private  SysUserEntity  sysUserEntity;

            public SysUserEntity getSysUserEntity() {
                return sysUserEntity;
            }

            public void setSysUserEntity(SysUserEntity sysUserEntity) {
                this.sysUserEntity = sysUserEntity;
            }
        
            //就医原因
                    private String content;
        
            //治疗费用
                    private Double fy;
        
            //治疗时间
                    private Date time;
        
    
            /**
         * 设置：
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * 获取：
         */
        public Integer getId() {
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
         * 设置：就医原因
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * 获取：就医原因
         */
        public String getContent() {
            return content;
        }
            /**
         * 设置：治疗费用
         */
        public void setFy(Double fy) {
            this.fy = fy;
        }

        /**
         * 获取：治疗费用
         */
        public Double getFy() {
            return fy;
        }
            /**
         * 设置：治疗时间
         */
        public void setTime(Date time) {
            this.time = time;
        }

        /**
         * 获取：治疗时间
         */
        public Date getTime() {
            return time;
        }
    }
