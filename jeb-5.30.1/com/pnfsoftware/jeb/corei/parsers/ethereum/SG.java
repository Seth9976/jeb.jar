package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SG {
   private static SG xK;
   List q = new ArrayList();
   Map RF = new HashMap();

   public static SG q() {
      if (xK == null) {
         xK = new SG();
      }

      return xK;
   }

   private SG() {
      this.xK();
      this.Dw();
      this.Uv();
      this.oW();
      this.gO();
      this.nf();
      this.gP();
      this.lm();
      this.zz();
      this.JY();
      this.za();
   }

   public List RF() {
      return this.q;
   }

   public SG.eo q(String var1) {
      return (SG.eo)this.RF.get(var1);
   }

   public List q(List var1) {
      ArrayList var2 = new ArrayList();

      for (SG.eo var4 : this.q) {
         if (var1.containsAll(var4.xK)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public SG.eo q(SG.eo var1) {
      if (this.RF.containsKey(var1.q())) {
         return null;
      } else {
         this.RF.put(var1.q(), var1);
         this.q.add(var1);
         return var1;
      }
   }

   private SG.eo xK() {
      return this.q(
         new SG.eo(
            "ERC20",
            null,
            new int[]{404098525, 1889567281, -1459249989, 599290589, 157198259, -580719298},
            new int[]{117300739, -1780966591, 826074471},
            new String[]{"Transfer(address,address,uint256)", "Approval(address,address,uint256)"}
         )
      );
   }

   private SG.eo Dw() {
      return this.q(new SG.eo("ERC165", null, new int[]{33540519}));
   }

   private SG.eo Uv() {
      return this.q(
         new SG.eo(
            "ERC721",
            new SG.eo[]{this.q("ERC165")},
            new int[]{1889567281, 1666326814, 157198259, 135795452, -1574128539, -377099835, 599290589, 1115958798, -1198698530},
            new int[0],
            new String[]{"Transfer(address,address,uint256)", "Approval(address,address,uint256)", "ApprovalForAll(address,address,bool)"}
         )
      );
   }

   private SG.eo oW() {
      return this.q(new SG.eo("ERC721TokenReceiver", null, new int[]{353073666}));
   }

   private SG.eo gO() {
      return this.q(new SG.eo("registerERC721Metadata", null, new int[]{117300739, -1780966591, -931440931}));
   }

   private SG.eo nf() {
      return this.q(new SG.eo("ERC721Enumerable", null, new int[]{404098525, 1332530407, 796154969}));
   }

   private SG.eo gP() {
      return this.q(new SG.eo("ERC820", null, new int[]{-267898288}));
   }

   private SG.eo za() {
      return this.q(
         new SG.eo("MultiSigWallet", null, new int[]{1885719368, 389555673, -503294234, -1169053985, -968723340, -1072001916, 552242566, -299736821})
      );
   }

   private SG.eo lm() {
      return this.q(new SG.eo("ERC223", null, new int[]{404098525, 117300739, -1780966591, 826074471, 1889567281, -1459249989, -1102709406}));
   }

   private SG.eo zz() {
      return this.q(
         new SG.eo(
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

   private SG.eo JY() {
      return this.q(new SG.eo("TokenFallback", null, new int[]{-1058141302}));
   }

   public static class eo {
      String q;
      List RF;
      List xK;
      List Dw;
      List Uv;

      eo(String var1, SG.eo[] var2, int[] var3) {
         this(var1, var2, var3, new int[0]);
      }

      eo(String var1, SG.eo[] var2, int[] var3, int[] var4) {
         this(var1, var2, var3, var4, new String[0]);
      }

      eo(String var1, SG.eo[] var2, int[] var3, int[] var4, String[] var5) {
         if (var1 == null) {
            throw new IllegalArgumentException("Provide a name");
         } else if (var3 != null && var3.length != 0) {
            this.q = var1;
            this.RF = var2 == null ? null : Arrays.asList(var2);
            this.xK = ArrayUtil.asList(var3);
            this.Dw = var4 == null ? Collections.emptyList() : ArrayUtil.asList(var4);
            this.Uv = var5 == null ? Collections.emptyList() : Arrays.asList(var5);
         } else {
            throw new IllegalArgumentException("Must have at least one public function");
         }
      }

      public String q() {
         return this.q;
      }

      public List RF() {
         return this.RF == null ? Collections.emptyList() : this.RF;
      }

      public List xK() {
         return this.xK;
      }

      public List Dw() {
         return this.Dw;
      }

      public List Uv() {
         return this.Uv;
      }

      @Override
      public String toString() {
         return this.q();
      }
   }
}
