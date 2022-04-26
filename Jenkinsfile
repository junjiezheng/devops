pipeline{
	//指定任务再哪个集群节点中执行
	agent any
	//声明全局变量，方便后面使用
	environment{
		key='value'
	}
	stages{
		stage('拉取git仓库代码'){
			  steps{
          			  checkout([$class: 'GitSCM', branches: [[name: '*/${GIT_BRANCH}']], extensions: [], 
                    userRemoteConfigs: [[credentialsId: 'f3c0ba02-def0-4846-af2a-682ce036d072', url: 'https://github.com/junjiezheng/devops.git']]])
			  }
		}
		stage('通过maven构建项目'){
			steps{
				sh "/var/jenkins_home/maven/bin/mvn clean package -Dmaven.test.skip=true -P${BULID_ENV}"
			}
		}
		stage('将自定义对像上传到目标服务器'){
			steps{
				echo '将自定义对像上传到目标服务器-success'
			}
		}
		stage('通过Publish Over SSH通知目标服务器'){
			steps{
				echo '通过Publish Over SSH通知目标服务器-success'
			}
		}
	}
}
