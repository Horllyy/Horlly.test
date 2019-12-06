package Password;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
/**
 * RSA加密、解密、测试正确性
 *
 */
public class RSA {
    public BigInteger[][] genKey(BigInteger p, BigInteger q){
        BigInteger n = p.multiply(q) ;
        BigInteger fy = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)) ;
        BigInteger e = new BigInteger("3889") ;
        // generate d
        BigInteger a = e ;
        BigInteger b = fy ;
        BigInteger[] rxy = new GCD().extGcd(a, b) ;
        BigInteger r = rxy[0] ;
        BigInteger x = rxy[1] ;
        BigInteger y = rxy[2] ;

        BigInteger d = x ;
        // 公钥  私钥
        return new BigInteger[][]{{n , e}, {n , d}} ;
    }

    /**
     * 加密
     * @param m 被加密的信息转化成为大整数m
     * @param pubkey 公钥
     * @return
     */
    public BigInteger encrypt(BigInteger m, BigInteger[] pubkey){
        BigInteger n = pubkey[0] ;
        BigInteger e = pubkey[1] ;

        BigInteger c = new Exponentiation().expMode(m, e, n) ;
        return c ;
    }

    /**
     * 解密
     * @param c
     * @param selfkey 私钥
     * @return
     */
    public BigInteger decrypt(BigInteger c, BigInteger[] selfkey){
        BigInteger n = selfkey[0] ;
        BigInteger d = selfkey[1] ;

        BigInteger m = new Exponentiation().expMode(c, d, n) ;
        return m ;
    }


    public static void main(String[] args) {
        // 公钥私钥中用到的两个大质数p,q'''
        BigInteger p = new BigInteger("106697219132480173106064317148705638676529121742557567770857687729397446898790451577487723991083173010242416863238099716044775658681981821407922722052778958942891831033512463262741053961681512908218003840408526915629689432111480588966800949428079015682624591636010678691927285321708935076221951173426894836169") ;
        BigInteger q = new BigInteger("144819424465842307806353672547344125290716753535239658417883828941232509622838692761917211806963011168822281666033695157426515864265527046213326145174398018859056439431422867957079149967592078894410082695714160599647180947207504108618794637872261572262805565517756922288320779308895819726074229154002310375209") ;

        RSA rsa = new RSA() ;
        // 生成公钥私钥'''
        // pubkey, selfkey = gen_key(p, q)
        BigInteger[][] keys = rsa.genKey(p, q) ;
        BigInteger[] pubkey  = keys[0] ;
        BigInteger[] selfkey = keys[1] ;

        //打印公钥
        System.out.print("公钥是：");
        for (BigInteger Apubkey:pubkey
             ) {
            System.out.print(Apubkey);
        }
        System.out.println(" ");

        //打印私钥
        System.out.print("私钥是：");
        for (BigInteger Aselfkey:selfkey
        ) {
            System.out.print(Aselfkey);
        }
        System.out.println(" ");

        // 需要被加密的信息转化成数字，长度小于秘钥n的长度，如果信息长度大于n的长度，那么分段进行加密，分段解密即可。'''
        BigInteger m = new BigInteger("1356205320457610288745198967657644166379972189839804389074591563666634066646564410685955217825048626066190866536592405966964024022236587593447122392540038493893121248948780525117822889230574978651418075403357439692743398250207060920929117606033490559159560987768768324823011579283223392964454439904542675637683985296529882973798752471233683249209762843835985174607047556306705224118165162905676610067022517682197138138621344578050034245933990790845007906416093198845798901781830868021761765904777531676765131379495584915533823288125255520904108500256867069512326595285549579378834222350197662163243932424184772115345") ;
        System.out.println("被加密信息：" + m);
        // 信息加密'''
        BigInteger c = rsa.encrypt(m, pubkey) ;
        System.out.println("密文：" + c);
        // 信息解密'''
        BigInteger d = rsa.decrypt(c, selfkey) ;
        System.out.println("被解密后信息：" + d);
    }
}

/**
 * 求最大公约数
 *
 */
class GCD {
    /**
     * <p>辗转相除法求最大公约数
     * @param a
     * @param b
     * @return
     */
    public BigInteger gcd(BigInteger a, BigInteger b){
        if(b.equals(BigInteger.ZERO)){
            return a ;
        }else{
            return gcd(b, a.mod(b)) ;
        }
    }
    /**
     * <p>扩展欧几里得算法：
     * <p>求ax + by = 1中的x与y的整数解（a，b互质）
     * @param a
     * @param b
     * @return
     */
    public BigInteger[] extGcd(BigInteger a, BigInteger b){
        if(b.equals(BigInteger.ZERO)){
            BigInteger x1 = BigInteger.ONE ;
            BigInteger y1 = BigInteger.ZERO ;
            BigInteger x = x1 ;
            BigInteger y = y1 ;
            BigInteger r = a ;
            BigInteger[] result = {r, x, y} ;
            return result ;
        }else{
            BigInteger[] temp = extGcd(b, a.mod(b)) ;
            BigInteger r  = temp[0] ;
            BigInteger x1 = temp[1] ;
            BigInteger y1 = temp[2] ;

            BigInteger x = y1 ;
            BigInteger y = x1.subtract(a.divide(b).multiply(y1)) ;
            BigInteger[] result = {r, x, y} ;
            return result ;
        }
    }
}


/**
 * 主要用于计算超大整数超大次幂然后对超大的整数取模。
 * 我在网上查询到这个算法叫做"蒙哥马利算法"。
 */
class Exponentiation {

    /**
     * 超大整数超大次幂然后对超大的整数取模
     (base ^ exponent) mod n
     * @param base
     * @param exponent
     * @param n
     * @return
     */
    public BigInteger expMode(BigInteger base, BigInteger exponent, BigInteger n){
        char[] binaryArray = new StringBuilder(exponent.toString(2)).reverse().toString().toCharArray() ;
        int r = binaryArray.length ;
        List<BigInteger> baseArray = new ArrayList<BigInteger>() ;

        BigInteger preBase = base ;
        baseArray.add(preBase);
        for(int i = 0 ; i < r - 1 ; i ++){
            BigInteger nextBase = preBase.multiply(preBase).mod(n) ;
            baseArray.add(nextBase) ;
            preBase = nextBase ;
        }
        BigInteger a_w_b = this.multi(baseArray.toArray(new BigInteger[baseArray.size()]), binaryArray, n) ;
        return a_w_b.mod(n) ;
    }


    private BigInteger multi(BigInteger[] array, char[] bin_array, BigInteger n){
        BigInteger result = BigInteger.ONE ;
        for(int index = 0 ; index < array.length ; index ++){
            BigInteger a = array[index] ;
            if(bin_array[index] == '0'){
                continue ;
            }
            result = result.multiply(a) ;
            result = result.mod(n) ;
        }
        return result ;
    }

}



