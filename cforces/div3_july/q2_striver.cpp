    #include <bits/stdc++.h> 
    #include <unordered_map> 
    #include <unordered_set>
    #include <algorithm>  
    using namespace std;
    #define mod (int)(1e9+7) 
    #define for0(i,n) for(int i=0;i<n;i++)
    #define for1(i,n) for(int i=1;i<=n;i++) 
    #define forab(i,a,b) for(int i=a;i<=b;i++) 
    #define tc(t) int t;in >>t;while(t--)
    #define ll long long
    #define pb(i) push_back(i)
    #define fio ios_base::sync_with_stdio(false),in.tie(NULL)
    #define in(x) scanf("%lld",&x)
    #define rr return 0
    #define mp make_pair
    const int N = (int)(2*1e6+5);
    ll mult(ll a,ll b, ll p=mod){return ((a%p)*(b%p))%p;}
    ll add(ll a, ll b, ll p=mod){return (a%p + b%p)%p;}
    ll fpow(ll n, ll k, ll p = mod) {ll r = 1; for (; k; k >>= 1) {if (k & 1) r = r * n%p; n = n * n%p;} return r;}
    int main()
    {
        #ifndef ONLINE_JUDGE
        // for getting input from input.txt
        freopen("input.txt", "r", stdin);
        // for writing output to output.txt
        freopen("output.txt", "w", stdout);
        #endif
        ll l; 
        cin >> l;
        string s,t; 
        cin >> s >> t; 

        int freq1[26];
        int freq2[26];
        memset(freq1,0,sizeof freq1); memset(freq2,0,sizeof freq2);

        for0(i,l)
        {
            freq1[s[i]-'a']+=1;
            freq2[t[i]-'a']+=1;
        }

        for(int i=0;i<26;i++)
        {
            if(freq1[i]!=freq2[i])
            {
                cout << -1; 
                rr;
            }
        }

        vector<ll>v;
        
             for(int i=0;i<l;i++)
             {
                if(s[i]!=t[i])
                {
                    for(int j=i+1;j<l;j++)
                    {
                        if(s[j]==t[i])
                        {
                            swap(s[j],s[j-1]);
                            i--;
                            v.pb(j);
                            break;
                        }
                    }
                }
                if(v.size()>(int)(1e4))
                {
                    cout << -1;
                    rr;
                }
             } 
        cout << v.size() << endl;

        for(int i=0;i<v.size();i++)
        {
            cout << v[i] << " ";
        }


       rr;
    }