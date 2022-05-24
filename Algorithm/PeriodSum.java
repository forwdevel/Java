package Algorithm;

// 합 배열을 이용하여, 시간 복잡도를 줄이기 위해

// 사용하는 특수한 목적의 알고리즘

// 구간 합 알고리즘을 활용하려면, 합 배열을 구해야 함

// 합배열
// S[i] = A[0] + ... + A[i];
// S[i] = S[i-1] + A[i];

// 구간 합 (i~j)
// S[j] - S[i-1]
