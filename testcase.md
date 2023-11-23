# 1. user account test (login, logout, change password, printprofile)
### incorrect password
```
1
student2
testpasssword1
```
### correct password
```
1
student2
testpasssword2
```
## (cont.) change password
```
1
newpasssword
```
## Logout 
```
3
```
## login - test new password
```
1
student2
newpasssword
```
## view profile
```go
2 // the student is from EEE
```
# 2. student - camp test
### View available camp (only one camp available to EEE)
```
4
```
### Login to student4 (SCSE) and view the available camp)
```
3
1
student4
testpasssword4
2
4
```
### View registered camp
```
6
```
### Register for camp 1
```go
5
1  // error -> user already registered 
// in camp1.txt there is student4 registered but it seem the registry is not linked
```
### View registered camp (also test sorting)
```go
6  // the camp is not registered suceessfully -> so no camp shown
```
### Register for other camp
```go
5
2 // register camp 2
6 // View camp registered
1 // sort by camp name
5 
3 // register camp 3
6
5 // sort by total number of slots
6
2 // sort by registration deadline
```

# 3. student - enquiry
### View enquiry
```
7
```
### Create new enquiry
```
9
1
What will be taught in this workshop?
7
1
```

### Create more enquiries
```
9
1
Where is the hardware lab 2?
9
1
Will lunch be provided?
```

### View enquiry list
