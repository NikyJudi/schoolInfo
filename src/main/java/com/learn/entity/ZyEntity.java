package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 
 *
 
 *  
 */
public class ZyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Integer id;
        
            //专业
                    private String name;
        
    
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
         * 设置：专业
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取：专业
         */
        public String getName() {
            return name;
        }
    }
