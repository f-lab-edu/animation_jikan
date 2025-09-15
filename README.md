# Animation jikan App
다양한 애니메이션 정보를 탐색할 수 있는 Android Applciation 입니다.</br>
</br>

### 기술스택
-  UI Framwork : Jetpack Compose
-  Networing: Retrofit & OkHttp
-  Database : Room
-  Dependency Injection : Hilt
-  Concurrency : Kotlin Coroutines & Flow
-  Image Loading : Coil

### 아키텍처(Architecture)
초기 개발은 빠른 개발 속도를 위해 **MVVM(Model - View - ViewModel)** 패턴을 기반으로 진행하며, </br>
추후 **Clean Architecture**로 점진적인 리팩터링을 계획 중</br>

#### 1단계 : MVVM + Repository Pattern
- Model
- View
- ViewModel

#### 2단계 : Clean Architecture
- Data Layer : 데이터 소스(API, DB) 관리
- Domain Layer : 비즈니스 규칙 및 유즈케이스 정의
- Presentation Layer : UI와 상태 관리

### 주요 기능
1. 추천 애니메이션, 만화
2. Top 애니메이션, 만화, 성우, 캐릭터
3. 리뷰 조회
4. 북마크 지정
5. 검색

  


