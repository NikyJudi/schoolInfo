package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 课程
 *
 
 *  
 */
public class KcEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Long id;
        
            //课程名称
                    private String name;
        
    
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
         * 设置：课程名称
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取：课程名称
         */
        public String getName() {
            return name;
        }
    }
