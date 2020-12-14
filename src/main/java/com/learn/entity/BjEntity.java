package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 班级
 *
 
 *  
 */
public class BjEntity implements Serializable {
    private static final long serialVersionUID = 1L;
            //数据库中bj表（班级表）包含两个字段id和name
    		//班级id---bigint(20),不为空，自增主键
                    private Long id;
    
            //班级名---varchar(255)
                    private String name;
        
    
        /**
         * 设置id
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * 获取id
         */
        public Long getId() {
            return id;
        }
            /**
         * 设置班级名
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取班级名
         */
        public String getName() {
            return name;
        }
    }
