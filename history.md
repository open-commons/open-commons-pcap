[2026/04/15]
- Modify
  + 배열 및 배열 데이터를 위한 'assert' 메소드 변경
    + open.commons.core.utils.ObjectUtils.requreNonNulls(...) -&gt; open.commons.core.utils.AssertUtils2.notNulls(...)
    
[2026/03/12]
- Dependencies
  + Delete
    + com.google.code.findbugs:jsr305:3.0.2
    
[2026/03/11]
- SNAPSHOT: 3.0.0-SHAPSHOT
- Migration JDK 1.8 to JDK 25
  + Updated
    + java.version: 25
    + compile.source.version: 25
    + compile.target.version: 25
    + maven-compiler-plugin.version: 3.13.0
    + maven-source-plugin.version: 3.3.1
    + maven-javadoc-plugin.version: 3.11.2
    + maven-jar-plugin.version: 3.4.2
    + maven-gpg-plugin.version: 3.2.4
    + disruptor.version: 4.0.0 ( <= com.lmax.distruptor.version)
    + opencsv.version: 5.9 ( <= net.sf.opencsv-version)
    + org.apache.logging.log4j-api.version: 2.24.3
    + slf4j-api.version: 2.0.16
    + jakarta-validation.version: 3.1.0
    + open.commons.core: 3.0.0-SNAPSHOT
  + Add
    + jakarta-annotation.version: 3.0.0
- Deprecated
  + @deprecated 대상 코드 및 파일 정리

[2025/07/24]
- Dependencies
  + Add
    + jakarta.validation:jakarata.validation-api:${managed-version}
    + com.google.code.findbugs:jsr305:${managed-version}

[2025/02/21] 
Apply 'Maven Central Deployment'

- Update
  + <deploymentManagement>
    + Release: Maven Central (https://central.sonatype.com)
  + 'open.commons' dependencies 
    + groupId: io.github.open-commons    
- Add
  + <build>
    + org.sonatype.central:central-publishing-maven-plugin
    + org.apache.maven.plugins:maven-gpg-plugin
    
[2025/02/17]
- Dependencies:
  + open-commons-core-dependencies: 2.1.0-SNAPSHOT
  
[2024/10/31]
- ETC
  + Maven Repository 주소 변경 (http -> https)
  
[2022/04/07]
- Release: 0.1.0
- Tag: 0.1.0
- Dependencies:
  + open.commons.core: 2.0.0-SNAPSHOT


[2022/04/07]
- Release: 0.1.0