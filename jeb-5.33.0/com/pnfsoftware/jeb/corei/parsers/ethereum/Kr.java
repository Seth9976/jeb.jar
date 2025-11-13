package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kr {
   private static Kr kS;
   List pC = new ArrayList();
   Map A = new HashMap();

   public static Kr pC() {
      if (kS == null) {
         kS = new Kr();
      }

      return kS;
   }

   private Kr() {
      this.A();
      this.kS();
      this.wS();
      this.UT();
      this.E();
      this.sY();
      this.ys();
      this.gp();
      this.oT();
      this.fI();
      this.ld();
   }

   public Kr.Av pC(String var1) {
      return (Kr.Av)this.A.get(var1);
   }

   public List pC(List var1) {
      ArrayList var2 = new ArrayList();

      for (Kr.Av var4 : this.pC) {
         if (var1.containsAll(var4.kS)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public Kr.Av pC(Kr.Av var1) {
      if (this.A.containsKey(var1.pC())) {
         return null;
      } else {
         this.A.put(var1.pC(), var1);
         this.pC.add(var1);
         return var1;
      }
   }

   private Kr.Av A() {
      return this.pC(
         new Kr.Av(
            "ERC20",
            null,
            new int[]{404098525, 1889567281, -1459249989, 599290589, 157198259, -580719298},
            new int[]{117300739, -1780966591, 826074471},
            new String[]{"Transfer(address,address,uint256)", "Approval(address,address,uint256)"}
         )
      );
   }

   private Kr.Av kS() {
      return this.pC(new Kr.Av("ERC165", null, new int[]{33540519}));
   }

   private Kr.Av wS() {
      return this.pC(
         new Kr.Av(
            "ERC721",
            new Kr.Av[]{this.pC("ERC165")},
            new int[]{1889567281, 1666326814, 157198259, 135795452, -1574128539, -377099835, 599290589, 1115958798, -1198698530},
            new int[0],
            new String[]{"Transfer(address,address,uint256)", "Approval(address,address,uint256)", "ApprovalForAll(address,address,bool)"}
         )
      );
   }

   private Kr.Av UT() {
      return this.pC(new Kr.Av("ERC721TokenReceiver", null, new int[]{353073666}));
   }

   private Kr.Av E() {
      return this.pC(new Kr.Av("registerERC721Metadata", null, new int[]{117300739, -1780966591, -931440931}));
   }

   private Kr.Av sY() {
      return this.pC(new Kr.Av("ERC721Enumerable", null, new int[]{404098525, 1332530407, 796154969}));
   }

   private Kr.Av ys() {
      return this.pC(new Kr.Av("ERC820", null, new int[]{-267898288}));
   }

   private Kr.Av ld() {
      return this.pC(
         new Kr.Av("MultiSigWallet", null, new int[]{1885719368, 389555673, -503294234, -1169053985, -968723340, -1072001916, 552242566, -299736821})
      );
   }

   private Kr.Av gp() {
      return this.pC(new Kr.Av("ERC223", null, new int[]{404098525, 117300739, -1780966591, 826074471, 1889567281, -1459249989, -1102709406}));
   }

   private Kr.Av oT() {
      return this.pC(
         new Kr.Av(
            "ERC223",
            null,
            new int[]{
               117300739,
               -1780966591,
               404098525,
               1889567281,
               1433341383,
               115639608,
               -1784968129,
               -86461654,
               -648322191,
               -1680229434,
               1655511939,
               -23227645,
               -60343217
            }
         )
      );
   }

   private Kr.Av fI() {
      return this.pC(new Kr.Av("TokenFallback", null, new int[]{-1058141302}));
   }

   public static class Av {
      String pC;
      List A;
      List kS;
      List wS;
      List UT;

      Av(String var1, Kr.Av[] var2, int[] var3) {
         this(var1, var2, var3, new int[0]);
      }

      Av(String var1, Kr.Av[] var2, int[] var3, int[] var4) {
         this(var1, var2, var3, var4, new String[0]);
      }

      Av(String var1, Kr.Av[] var2, int[] var3, int[] var4, String[] var5) {
         if (var1 == null) {
            throw new IllegalArgumentException("Provide a name");
         } else if (var3 != null && var3.length != 0) {
            this.pC = var1;
            this.A = var2 == null ? null : Arrays.asList(var2);
            this.kS = ArrayUtil.asList(var3);
            this.wS = var4 == null ? Collections.emptyList() : ArrayUtil.asList(var4);
            this.UT = var5 == null ? Collections.emptyList() : Arrays.asList(var5);
         } else {
            throw new IllegalArgumentException("Must have at least one public function");
         }
      }

      public String pC() {
         return this.pC;
      }

      @Override
      public String toString() {
         return this.pC();
      }
   }
}
