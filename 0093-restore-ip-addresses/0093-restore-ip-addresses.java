class Solution {
    List<String> result=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if(s.length()<4||s.length()>12) return result;
        helper(s,0,0,new StringBuilder ());
        return result;
    }
    public void helper(String s,int startIdx,int count,StringBuilder sb){
        if(count==4){
            if(startIdx==s.length()){
                result.add(sb.toString());
                
            }
            return;
        }
        for(int len=1;len<=3;len++){
            if(startIdx+len>s.length()) break;
            String str=s.substring(startIdx,startIdx+len);
            if(isValid(str)){
                int prevlength=sb.length();
                sb.append(str);
                if(count<3) sb.append(".");
                helper(s,startIdx+len,count+1,sb);
                sb.setLength(prevlength);
            }
        }
    }
    public boolean isValid(String str){
        if(str.charAt(0)=='0'&&str.length()>1) return false;
        int value=Integer.parseInt(str);
        return value>=0&&value<=255;
    }
}