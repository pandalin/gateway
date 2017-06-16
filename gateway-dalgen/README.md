MyBatis 配置生成工具，能够自动生成DO实体、DAO接口和Mapper文件

//DalgenDemo


	public class DalgenDemo extends AbstractDalgenTask {

		protected void init() {
			//项目名
			setAppName("project");
			//dal放置的包路径
			setDalPackage("com.yiji.project.dal");
	
			//每个Do都继承的类
			setExtendsEntity("EntityDO");
	
			//设置Dal模块的路径，如果不指定，默认是：project-dal 模块
			//setDalModulePath("D:\\MiddlegenTaskTest");
	
			//设置数据库
	//		setDataBaseInfo("jdbc:oracle:thin:@localhost:1521:orcl", "username", "password");
			setDataBaseInfo("jdbc:mysql://localhost:3306/yjf_database", "username", "password");
	
			//多个table逗号分隔
			String tables = "table1,table2,table3,.......";
			setTables(tables);
		}
	
		public static void main(String[] args) {
			DalgenDemo dalgen = new DalgenDemo();
			dalgen.start();
		}
	}
```

然后运行即可：
main函数即可生成Mybatis配置


更多配置：


	/**
	 * 应用名称
	 */
	public void setAppName(String appName);

	/**
	 * dal包路径
	 *
	 */
	public void setDalPackage(String dalPackage);

	/**
	 * 生成的DO的包名，即[dalPackage].[doPackage]
	 * 默认：entity
	 */
	public void setDoPackage(String doPackage);

	/**
	 * 生成的DAO的包名，即[dalPackage].[daoPackage]
	 * 默认：mapper
	 */
	public void setDaoPackage(String daoPackage);

	/**
	 * 生成的接口(DAO)的后缀
	 * 默认 middlegen.AbstractDalgenTask#DEFAULT_DAO_SUFFIX
	 */
	public void setDaoSuffix(String daoSuffix);

	/**
	 * 生成的实体(DO)的后缀
	 * 默认：middlegen.AbstractDalgenTask#DEFAULT_DO_SUFFIX
	 */
	public void setDoSuffix(String doSuffix);

	/**
	 * 生成的Mapper.xml的后缀
	 * 默认 AbstractDalgenTask#DEFAULT_MAPPER_SUFFIX
	 */
	public void setMapperSuffix(String mapperSuffix)；

	/**
	 * 生成的实体(DO) 实现的的接口
	 */
	public void setImplementsEntity(String implementsEntity)；

	/**
	 * 生成的接口(DAO) 实现的接口
	 */
	public void setImplementsMapper(String implementsMapper)；

	/**
	 * 生成的实体(DO)继承的类
	 * @param extendsEntity
	 */
	public void setExtendsEntity(String extendsEntity)；


	/**
	 * insert语句是否需要 selectKey
	 */
	public void setUseSelectKey(boolean useSelectKey)；

	/**
	 * 数据库连接
	 */
	public void setDataBaseInfo(String url, String username, String password)；

	/**
	 * 生成的 哪些Table
	 *
	 */
	public void setTables(String tables)；

	/**
	 * 生成的Mapper.xml配置文件中，是否需要指定javaType，默认需要
	 *
	 */
	public void setUseJavaType(boolean useJavaType)；

	/**
	 * 生成的Mapper.xml配置文件中，是否需要指定jdbcType，默认需要
	 *
	 */
	public void setUseJdbcType(boolean useJdbcType)；

	/**
	 * 设置生成的对象，默认DO，DAO，Mapper
	 * GENERATE_DO+GENERATE_DAO+GENERATE_MAPPER
	 * @see AbstractDalgenTask#GENERATE_DO
	 * @see AbstractDalgenTask#GENERATE_DAO
	 * @see AbstractDalgenTask#GENERATE_MAPPER
	 */
	public void setGenerate(int... generates);

	/**
	 * 设置生成SQL的类型，默认all
	 * @see GenerateSqlType
	 */
	public void setGenerateSqlType(int... generates) ；

	/**
	 * 默认在dal/src/main/resources/templates
	 *
	 */
	public void setTemplatesPath(String templatesPath)；

	/**
	 * dal所在模块，默认[appName]-[dalModulePath]
	 * @param dalModulePath
	 */
	public void setDalModulePath(String dalModulePath)；
 


更多高级用法说明，后期更新