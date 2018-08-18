// CF Educational 498 
// Striver_79
#include <bits/stdc++.h>
using namespace std;
#define mod (int)(1e9+7) 
#define for0(i,n) for(int i=0;i<n;i++)
#define for1(i,n) for(int i=1;i<=n;i++) 
#define forab(i,a,b) for(int i=a;i<=b;i++) 
#define tc(t) int t;cin >>t;while(t--)
#define ll long long
#define pb(i) push_back(i)
#define fio ios_base::sync_with_stdio(false),cin.tie(NULL)
#define in(x) scanf("%lld",&x)
#define rr return 0
#define mp make_pair
const int N = 100005;
ll mult(ll a,ll b, ll p=mod){return ((a%p)*(b%p))%p;}
ll add(ll a, ll b, ll p=mod){return (a%p + b%p)%p;}
ll fpow(ll n, ll k, ll p = mod) {ll r = 1; for (; k; k >>= 1) {if (k & 1) r = r * n%p; n = n * n%p;} return r;}
ll inv(ll a, ll p = mod) {return fpow(a, p - 2, p);} 
ll SPF[N+10]; 
void SOF()
{
    SPF[1] = 1;
    for (int i=2; i<N; i++)
        SPF[i] = i;
    for (int i=4; i<N; i+=2)
        SPF[i] = 2;
 
    for (int i=3; i*i<N; i++)
        {
        if (SPF[i] == i)
            {
            for (int j=i*i; j<N; j+=i)
                if (SPF[j]==j)
                   SPF[j] = i;
            }
        }
}
int main()
{
    ll n; 
    cin >> n;
    ll a[n];
    for0(i,n) cin>> a[i]; 
    ll suf=0; 
    map<ll,ll>mp; 
    for(int i=n-1;i>=0;i--)
    {
        suf+=a[i]; 
        mp[suf]=i;
    }

    ll pre=0;
    ll maxi=-1; 
    for0(i,n)
    {
        pre+=a[i];
        if(mp[pre]>i)
        {
            if(pre>maxi)
            {
                maxi=pre;
            }
        }
    }
    if(maxi==-1)
    cout << 0; 
    else 
    cout << maxi;
    rr;
}
