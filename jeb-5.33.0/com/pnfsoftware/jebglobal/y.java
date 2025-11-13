package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class y {
   private static final ILogger pC = GlobalLog.getLogger(y.class);
   private bA A;
   private MultiMap kS;
   private Map wS = new HashMap();
   private Map UT = new HashMap();
   private Map E = new HashMap();
   private LP sY;
   private LP ys;
   private LP ld;
   private LP gp;

   y(bA var1) {
      this.A = var1;
   }

   public Jx pC(PZ var1, long var2) throws IOException, wX {
      byte var4;
      long var5;
      long var7;
      switch (var1.pC) {
         case 0:
            return null;
         case 1:
            var4 = -2;
            var5 = this.A(var2, var1.kS);
            var7 = 0L;
            break;
         case 2:
            var4 = -3;
            var5 = this.pC(var2, var1.kS);
            var7 = 0L;
            break;
         case 3:
            var4 = 1;
            var5 = this.pC(var2, var1.kS);
            var7 = var1.wS;
            break;
         default:
            return null;
      }

      return var5 == -1L ? null : new Jx(var4, var2, var5, var7);
   }

   public List pC(PZ var1) throws IOException, wX {
      ArrayList var2 = new ArrayList();

      for (long var4 : this.A(var1.A)) {
         Jx var6 = this.pC(var1, var4);
         if (var6 != null) {
            var2.add(var6);
         }
      }

      return var2;
   }

   private String wS(String var1) {
      if (var1.equalsIgnoreCase("string")) {
         return "Ljava/lang/String;";
      } else {
         if (var1.length() == 1) {
            switch (var1.charAt(0)) {
               case 'B':
                  return "Ljava/lang/Byte;";
               case 'C':
                  return "Ljava/lang/Character;";
               case 'D':
                  return "Ljava/lang/Double;";
               case 'E':
               case 'G':
               case 'H':
               case 'K':
               case 'L':
               case 'M':
               case 'N':
               case 'O':
               case 'P':
               case 'Q':
               case 'R':
               case 'T':
               case 'U':
               case 'V':
               case 'W':
               case 'X':
               case 'Y':
               default:
                  break;
               case 'F':
                  return "Ljava/lang/Float;";
               case 'I':
                  return "Ljava/lang/Integer;";
               case 'J':
                  return "Ljava/lang/Long;";
               case 'S':
                  return "Ljava/lang/Short;";
               case 'Z':
                  return "Ljava/lang/Boolean;";
            }
         }

         return var1;
      }
   }

   private void UT(boolean var1) throws IOException, wX {
      if (var1) {
         this.kS = null;
      }

      if (this.kS == null) {
         this.kS = new MultiMap();

         try {
            im var2 = this.A.wS.ys();

            for (gW var6 : var2.pC) {
               this.kS.put(var6.kS, var6);
            }
         } catch (oY var7) {
            throw new wX(var7, "Cannot retrieve types");
         }
      }
   }

   public Collection pC(boolean var1) throws IOException, wX {
      this.UT(var1);
      return this.kS.values();
   }

   public List pC(String var1) throws IOException, wX {
      boolean var2 = this.kS != null;
      this.UT(false);
      var1 = this.wS(var1);
      List var3 = this.kS.get(var1);
      if (var3 == null) {
         ArrayList var4 = new ArrayList();

         for (String var6 : this.kS.keySet()) {
            if (var6.contains(var1)) {
               var4.add(var6);
            }
         }

         if (var4.isEmpty()) {
            if (var2) {
               this.kS = null;
               return this.pC(var1);
            }

            return null;
         }

         if (var4.size() >= 2) {
            throw new wX("Ambiguous type for \"%s\" (%d candidates)", var1, var4.size());
         }

         var3 = this.kS.get((String)var4.get(0));
      }

      return var3;
   }

   public List pC(long var1, String var3, String var4) throws IOException, wX {
      jG var5;
      try {
         var5 = this.A.wS.wS(var1);
      } catch (oY var11) {
         throw new wX(var11, "Error while trying to retrieve methods of class %d", var1);
      }

      if (Strings.isBlank(var3) && Strings.isBlank(var4)) {
         return Arrays.asList(var5.pC);
      } else {
         ArrayList var6 = new ArrayList();

         for (eO var10 : var5.pC) {
            if (var10.A.equals(var3) || var3 == null) {
               var6.add(var10);
            }
         }

         if (var6.isEmpty()) {
            for (eO var19 : var5.pC) {
               if (var19.A.contains(var3)) {
                  var6.add(var19);
               }
            }
         }

         if (var6.size() >= 1 && var4 != null && !"()".equals(var4)) {
            ArrayList var13 = new ArrayList();

            for (eO var18 : var6) {
               if (var18.kS.equals(var4)) {
                  var13.add(var18);
               }
            }

            if (var13.isEmpty()) {
               StringBuilder var16 = new StringBuilder();
               var6.forEach(var1x -> var16.append(var1x.kS).append(", "));
               var16.delete(var16.length() - 2, var16.length());
               pC.info(S.L("No matching signature found. Available signatures are: %s"), var16);
            }

            var6 = var13;
         }

         return var6;
      }
   }

   private eO A(long var1, long var3) throws IOException, wX {
      for (eO var7 : this.pC(var1, null, null)) {
         if (var7.pC == var3) {
            return var7;
         }
      }

      return null;
   }

   public eO A(long var1, String var3, String var4) throws IOException, wX {
      List var5 = this.pC(var1, var3, var4);
      return var5.isEmpty() ? null : (eO)var5.get(0);
   }

   public Nv[] pC(long var1) throws IOException, wX {
      synchronized (this.wS) {
         Nv[] var4 = (Nv[])this.wS.get(var1);
         if (var4 == null) {
            try {
               var4 = this.A.wS.kS(var1).pC;
               this.wS.put(var1, var4);
            } catch (oY var7) {
               throw new wX(var7, "Error while trying to retrieve fields of class %d", var1);
            }
         }

         return var4;
      }
   }

   public Nv kS(long var1, String var3, String var4) throws IOException, wX {
      Nv[] var5 = this.pC(var1);
      ArrayList var6 = new ArrayList();

      for (Nv var10 : var5) {
         if (var10.A.equals(var3)) {
            var6.add(var10);
         }
      }

      if (var6.isEmpty()) {
         for (Nv var17 : var5) {
            if (var17.A.contains(var3)) {
               var6.add(var17);
            }
         }
      }

      if (var6.size() >= 1 && var4 != null) {
         ArrayList var12 = new ArrayList();

         for (Nv var16 : var6) {
            if (var16.kS.equals(var4)) {
               var12.add(var16);
            }
         }

         var6 = var12;
      }

      return var6.isEmpty() ? null : (Nv)var6.get(0);
   }

   public JV A(long var1) throws IOException, wX {
      synchronized (this.UT) {
         JV var4 = (JV)this.UT.get(var1);
         if (var4 == null) {
            try {
               var4 = this.A.wS.A(var1);
            } catch (oY var7) {
               pC.catching(var7);
               throw new wX(var7, "Error while trying to retrieve class %d", var1);
            }

            this.UT.put(var1, var4);
         }

         return var4;
      }
   }

   public String kS(long var1) throws IOException, wX {
      return this.A(var1).pC;
   }

   public List A(String var1) throws IOException, wX {
      ArrayList var2 = new ArrayList();
      long var3 = Conversion.stringToLong(var1);
      if (var3 != 0L) {
         var2.add(var3);
      } else {
         List var5 = this.pC(var1);
         if (var5 != null) {
            for (gW var7 : var5) {
               var2.add(var7.A);
            }
         }
      }

      return var2;
   }

   public long kS(String var1) throws IOException, wX {
      List var2 = this.A(var1);
      return var2.isEmpty() ? -1L : (Long)var2.get(0);
   }

   public String pC(long var1, long var3) throws IOException, wX {
      for (eO var7 : this.pC(var1, null, null)) {
         if (var7.pC == var3) {
            return Strings.ff("%s%s", var7.A, var7.kS);
         }
      }

      return null;
   }

   public long pC(long var1, String var3) throws IOException, wX {
      long var4 = Conversion.stringToLong(var3);
      if (var4 != 0L) {
         return var4;
      } else {
         String var6 = var3;
         String var7 = null;
         int var8 = var3.indexOf(40);
         if (var8 != -1) {
            var6 = var3.substring(0, var8);
            var7 = var3.substring(var8);
         }

         return this.wS(var1, var6, var7);
      }
   }

   public long wS(long var1, String var3, String var4) throws IOException, wX {
      eO var5 = this.A(var1, var3, var4);
      return var5 == null ? -1L : var5.pC;
   }

   public eO pC(long var1, String var3, List var4) throws IOException, wX {
      long var5 = Conversion.stringToLong(var3);
      if (var5 != 0L) {
         return this.A(var1, var5);
      } else {
         eO var7 = null;
         String var8 = var3;
         String var9 = null;
         int var10 = var3.indexOf(40);
         if (var10 != -1) {
            var8 = var3.substring(0, var10);
            var9 = var3.substring(var10);
         }

         while (var1 != 0L && var7 == null) {
            List var11 = this.pC(var1, var8, var9);
            var7 = this.pC(var8, var11, var4);
            var1 = this.sY(var1);
         }

         return var7;
      }
   }

   private eO pC(String var1, List var2, List var3) throws IOException, wX {
      if (var2.isEmpty()) {
         return null;
      } else {
         TreeSet var4 = new TreeSet();

         for (eO var6 : var2) {
            if (var6.A.equals(var1)) {
               String var7 = var6.kS;
               if (var7.charAt(0) == '(') {
                  String var8 = var7.substring(1, var7.indexOf(41));
                  List var9 = this.UT(var8);
                  if (var3.size() == var9.size()) {
                     boolean var10 = true;

                     for (int var11 = 0; var11 < var3.size(); var11++) {
                        if (!this.pC((rG)var3.get(var11), (String)var9.get(var11))) {
                           var10 = false;
                           break;
                        }
                     }

                     if (var10) {
                        return var6;
                     }
                  }
               }

               var4.add(var6.kS);
            }
         }

         if (!var4.isEmpty()) {
            StringBuilder var12 = new StringBuilder("Invalid arguments for ").append(var1);
            if (var4.size() == 1) {
               var12.append(". Signature: ").append((String)var4.iterator().next());
            } else {
               var12.append(". Valid signatures: ").append(Strings.join(", ", var4));
            }

            throw new wX(var12.toString());
         } else {
            return null;
         }
      }
   }

   private List UT(String var1) {
      ArrayList var2 = new ArrayList();

      while (!var1.isEmpty()) {
         String var3 = this.E(var1);
         var2.add(var3);
         var1 = var1.substring(var3.length());
      }

      return var2;
   }

   private String E(String var1) {
      switch (var1.charAt(0)) {
         case 'L':
            return var1.substring(0, var1.indexOf(59) + 1);
         case '[':
            return "[" + this.E(var1.substring(1));
         default:
            return var1.substring(0, 1);
      }
   }

   public boolean pC(rG var1, String var2) throws IOException, wX {
      byte var3 = var1.pC;
      switch (var3) {
         case 66:
            return "B".equals(var2);
         case 67:
            return "C".equals(var2);
         case 68:
            return "D".equals(var2);
         case 69:
         case 71:
         case 72:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         default:
            if (var2.startsWith("L")) {
               if (var2.equals("Ljava/lang/Object;")) {
                  return true;
               }

               long var7 = this.A.fI().pC(var1.A);
               return this.kS(var7, var2);
            } else {
               if (var2.startsWith("[") && var3 == 91) {
                  long var4 = this.A.fI().pC(var1.A);

                  String var6;
                  for (var6 = this.kS(var4); var2.startsWith("["); var6 = var6.substring(1)) {
                     var2 = var2.substring(1);
                     if (!var6.startsWith("[")) {
                        return false;
                     }
                  }

                  if (var6.startsWith("[")) {
                     return false;
                  }

                  return this.kS(this.kS(var6), var2);
               }

               return false;
            }
         case 70:
            return "F".equals(var2);
         case 73:
            return "I".equals(var2);
         case 74:
            return "J".equals(var2);
         case 83:
            return "S".equals(var2);
         case 90:
            return "Z".equals(var2);
      }
   }

   private boolean kS(long var1, String var3) throws IOException, wX {
      String var4 = this.kS(var1);
      long var5 = this.kS(var3);
      if (!var4.equals(var3) && var5 != var1) {
         long var7 = this.sY(var1);
         if (var7 == 0L) {
            return false;
         } else if (this.kS(var7, var3)) {
            return true;
         } else {
            for (long var11 : this.ys(var1)) {
               if (this.kS(var11, var3)) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   public long A(long var1, String var3) throws IOException, wX {
      long var4 = Conversion.stringToLong(var3);
      if (var4 != 0L) {
         return var4;
      } else {
         String var6 = var3;
         String var7 = null;
         int var8 = var3.indexOf(58);
         if (var8 != -1) {
            var6 = var3.substring(0, var8);
            var7 = var3.substring(var8 + 1);
         }

         return this.UT(var1, var6, var7);
      }
   }

   public long UT(long var1, String var3, String var4) throws IOException, wX {
      Nv var5 = this.kS(var1, var3, var4);
      return var5 == null ? -1L : var5.pC;
   }

   void pC() throws IOException {
      if (!this.A.pC()) {
         throw new IOException("The debugger must be connected in order to complete the operation");
      }
   }

   public String A() {
      try {
         this.pC();
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "VM information: %s\n", this.A.wS.pC());
         Strings.ff(var1, "VM identifier sizes: %s", this.A.wS.wS());
         return var1.toString();
      } catch (oY | IOException var2) {
         pC.catching(var2);
         return null;
      }
   }

   public boolean kS() {
      try {
         this.pC();
         this.A.wS.E();
         return true;
      } catch (oY | IOException var2) {
         pC.catching(var2);
         return false;
      }
   }

   public boolean wS() {
      try {
         this.pC();
         this.A.wS.UT();
         return true;
      } catch (oY | IOException var2) {
         pC.catching(var2);
         return false;
      }
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public boolean UT() {
      List var1;
      try {
         var1 = this.E();
         if (var1 == null) {
            return false;
         }
      } catch (oY | IOException var8) {
         pC.catching(var8);
         return false;
      }

      Iterator var2;
      try {
         var2 = var1.iterator();
      } catch (oY | IOException var6) {
         pC.catching(var6);
         return false;
      }

      while (true) {
         try {
            if (!var2.hasNext()) {
               return true;
            }

            long var3 = (Long)var2.next();
            int var5 = this.A.wS.WR(var3);
            Object[] var10000 = new Object[]{var3, var5};
            if (var5 == 0) {
               return false;
            }
         } catch (oY | IOException var7) {
            pC.catching(var7);
            return false;
         }
      }
   }

   public List E() {
      try {
         this.pC();
         Mw var1 = this.A.wS.A();
         return ArrayUtil.asList(var1.A);
      } catch (oY | IOException var2) {
         pC.catching(var2);
         return null;
      }
   }

   public String wS(long var1) {
      try {
         return this.A.wS.ys(var1);
      } catch (oY | IOException var4) {
         pC.catching(var4);
         return null;
      }
   }

   public boolean UT(long var1) {
      try {
         this.A.wS.ld(var1);
         return true;
      } catch (oY | IOException var4) {
         pC.catching(var4);
         return false;
      }
   }

   public boolean pC(long var1, boolean var3) {
      try {
         int var4 = this.A.wS.WR(var1);

         do {
            this.A.wS.pC(var1, var3);
         } while (--var4 > 0);

         return true;
      } catch (oY | IOException var5) {
         pC.catching(var5);
         return false;
      }
   }

   public DebuggerThreadStatus E(long var1) {
      try {
         lS var3 = this.A.wS.oT(var1);
         if (var3.A == 1) {
            return DebuggerThreadStatus.PAUSED;
         } else {
            switch (CI.pC(var3.pC)) {
               case UT:
                  return DebuggerThreadStatus.MONITOR;
               case kS:
                  return DebuggerThreadStatus.RUNNING;
               case wS:
                  return DebuggerThreadStatus.SLEEPING;
               case E:
                  return DebuggerThreadStatus.WAITING;
               case A:
                  return DebuggerThreadStatus.ZOMBIE;
               default:
                  return DebuggerThreadStatus.UNKNOWN;
            }
         }
      } catch (oY | IOException var4) {
         pC.catching(var4);
         return DebuggerThreadStatus.UNKNOWN;
      }
   }

   public boolean A(boolean var1) {
      try {
         if (var1) {
            this.A(false);
            boolean var2 = false;
            if (this.sY == null) {
               Jh var3 = var2 ? Jh.A : Jh.pC;
               this.sY = this.pC(lz.E, var3, null);
            }

            if (this.ys == null) {
               this.ys = this.pC(lz.sY, Jh.pC, null);
            }
         } else {
            if (this.sY != null) {
               this.pC(this.sY);
               this.sY = null;
            }

            if (this.ys != null) {
               this.pC(this.ys);
               this.ys = null;
            }
         }

         return true;
      } catch (oY | IOException var4) {
         pC.catching(var4);
         return false;
      }
   }

   public boolean kS(boolean var1) {
      try {
         if (var1) {
            if (this.ld == null) {
               this.ld = this.pC(lz.ys, Jh.pC, null);
            }
         } else if (this.ld != null) {
            this.pC(this.ld);
            this.ld = null;
         }

         return true;
      } catch (oY | IOException var3) {
         pC.catching(var3);
         return false;
      }
   }

   public boolean wS(boolean var1) {
      try {
         if (var1) {
            if (this.gp == null) {
               Fz var2 = new Fz();
               AM var3 = AM.pC(0L, false, true);
               var2.pC(var3);
               this.gp = this.pC(lz.wS, this.A.vP(), var2);
            }
         } else if (this.gp != null) {
            this.pC(this.gp);
            this.gp = null;
         }

         return true;
      } catch (oY | IOException var4) {
         pC.catching(var4);
         return false;
      }
   }

   public LP pC(lz var1, Jh var2, Fz var3) throws IOException, oY {
      int var4 = this.A.gp().pC(var1, var2, var3);
      Object[] var10000 = new Object[]{var4, var1};
      return new LP(var1, var4);
   }

   public void pC(LP var1) throws IOException, oY {
      Object[] var10000 = new Object[]{var1.A()};
      this.A.gp().pC(var1.pC(), var1.A());
   }

   public long sY(long var1) throws IOException, wX {
      synchronized (this.E) {
         Long var4 = (Long)this.E.get(var1);
         if (var4 == null) {
            try {
               var4 = this.A.wS.UT(var1);
               this.E.put(var1, var4);
            } catch (oY var7) {
               throw new wX(var7, "Error while retrieving superclass of class %d", var1);
            }
         }

         return var4;
      }
   }

   public List ys(long var1) throws IOException, wX {
      try {
         return this.A.wS.pC(var1);
      } catch (oY var4) {
         throw new wX(var4, "Error while retrieving interfaces of class %d", var1);
      }
   }

   public rG pC(long var1, long var3, long var5, String var7, List var8) throws wX, IOException {
      eO var9 = this.pC(var5, var7, var8);
      if (var9 == null) {
         throw new wX(Strings.ff("Invalid method %s for class %s", var7, this.kS(var5)));
      } else if (var9.pC(8)) {
         try {
            return this.A.gp().pC(var5, var1, var9.pC, var8);
         } catch (oY var11) {
            throw new wX(var11, this.pC(var11, var9.pC, var5, true));
         }
      } else if (var3 <= 0L) {
         throw new wX(Strings.ff("Method %s is not static", var7));
      } else {
         try {
            return this.A.gp().pC(var3, var1, var5, var9.pC, var8);
         } catch (oY var12) {
            throw new wX(var12, this.pC(var12, var9.pC, var3, false));
         }
      }
   }

   private String pC(oY var1, long var2, long var4, boolean var6) {
      return Strings.ff(
         "Error while trying to invoke %s method %d on %s %d%s",
         var6 ? "static" : "instance",
         var2,
         var6 ? "class" : "object",
         var4,
         var1.pC() == 10 ? ": Method invocation is not supported when the target VM has been suspended by the front-end, it must be suspended by an event" : ""
      );
   }

   public rG pC(long var1, long var3, List var5) throws wX, IOException {
      eO var6 = this.pC(var3, "<init>", var5);

      try {
         return this.A.gp().A(var3, var1, var6.pC, var5);
      } catch (oY var8) {
         throw new wX(var8, this.pC(var8, var6.pC, var3));
      }
   }

   private String pC(oY var1, long var2, long var4) {
      return Strings.ff(
         "Error while trying to instanciate %d of class %d%s",
         var2,
         var4,
         var1.pC() == 10 ? ": Method invocation is not supported when the target VM has been suspended by the front-end, it must be suspended by an event" : ""
      );
   }
}
