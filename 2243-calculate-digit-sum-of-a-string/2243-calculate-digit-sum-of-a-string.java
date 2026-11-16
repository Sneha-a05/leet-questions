class Solution {
    public String digitSum(String s, int k) {
        String res=s;
        while(res.length()>k){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<res.length();i+=k){
                int m=0;
                for(int j=i;j<res.length() && j<i+k;j++){
                    char ch=res.charAt(j);
                    m+=(ch-'0');
                }
                sb.append(m);
            }
            res=sb.toString();
        }
        return res;
    }
}