# 문제

서준이는 아빠로부터 N개의 회의와 하나의 회의실을 선물로 받았다. 각 회의는 시작 시간, 끝나는 시간, 회의 인원이 주어지고 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다. 단, 회의는 한번 시작되면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작 시간은 끝나는 시간보다 항상 작다. N개의 회의를 회의실에 효율적으로 배정할 경우 회의를 진행할 수 있는 최대 인원을 구하자.

# 입력

첫째 줄에 회의의 수 N이 주어진다. 둘째 줄부터 N + 1 줄까지 공백을 사이에 두고 회의의 시작시간, 끝나는 시간, 회의 인원이 주어진다.

# 출력

첫째 줄에 회의실에서 회의를 진행할 수 있는 최대 인원을 출력한다.

# 제한

- 1 ≤ N ≤ 100,000
- N개의 회의 중 어떤 두 개의 회의도 회의 시간이 겹칠 수 있다.
- 모든 회의의 시작 시간과 끝나는 시간은 2<sup>31</sup> − 1보다 작거나 같은 자연수 또는 0이다.
- 모든 회의의 시작 시간과 끝나는 시간은 서로 다르다.
- 회의 인원은 1,000보다 작거나 같은 자연수 이다.

# 예제 입력 1

```
5
10 50 50
30 60 60
20 120 100
80 100 50
110 140 70
```

# 예제 출력 1

```
180
```

# 예제 입력 2

```
5
10 20 50
30 60 60
25 70 100
80 90 50
11 100 70
```

# 예제 출력 2

```
200
```
