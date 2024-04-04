// usersTable \
`aws cloudformation create-stack --region us-west-2 --stack-name todolist-userstable01 --template-body file://template_java_project/docs/usersTable.yaml --capabilities CAPABILITY_IAM`

// tasksTable \
`aws cloudformation create-stack --region us-west-2 --stack-name todolist-taskstable01 --template-body file://template_java_project/docs/tasksTable.yaml --capabilities CAPABILITY_IAM`