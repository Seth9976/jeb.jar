package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.encoding.zip.GenericZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.IGenericZipBrowser;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.DB;
import com.pnfsoftware.jebglobal.WD;
import com.pnfsoftware.jebglobal.hP;
import com.pnfsoftware.jebglobal.lO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ws implements RC {
   private static final ILogger pC = GlobalLog.getLogger(Ws.class);
   private File A;
   private IGenericZipBrowser kS;
   private Map wS;
   private Map UT = new HashMap();
   private Sv E;
   private uX sY;
   private GK ys;
   private Map ld;
   private Boolean gp;
   private String oT;

   public Ws(File var1, Sv var2) throws IOException {
      this.A = var1;
      this.kS = Sv.pC(this.A);
      this.E = var2;
      this.sY = new uX((RC)(var2 != null ? var2 : this));
   }

   public File pC() {
      return this.A;
   }

   public boolean A() {
      if (this.gp == null) {
         return this.E != null ? this.E.pC() : false;
      } else {
         return this.gp;
      }
   }

   public int kS() {
      this.pC(0);
      if (this.wS.isEmpty()) {
         throw new RuntimeException("Arsc package map is empty!");
      } else {
         return (Integer)this.wS.keySet().iterator().next();
      }
   }

   public synchronized boolean pC(int var1) {
      if (this.wS == null) {
         this.wS = Collections.emptyMap();
         if (this.kS.getEntry("resources.arsc") != null) {
            try (InputStream var2 = this.kS.getEntryStream("resources.arsc")) {
               this.wS = DB.pC(var2);
            } catch (IOException var7) {
               if (this.A()) {
                  throw new RuntimeException(var7);
               }

               pC.catching(var7);
               JebCoreService.notifySilentExceptionToClient(new K("Failed parsing arsc header for apk " + this.A.getName(), var7));
            }
         }
      }

      return this.wS.containsKey(var1);
   }

   public String pC(boolean var1) throws K {
      if (!this.wS().sY()) {
         throw new K("resources.arsc was not successfully parsed");
      } else {
         return new sy(this.ys, var1).pC();
      }
   }

   public KD wS() {
      return this.pC("resources.arsc");
   }

   public KD pC(String var1) {
      KD var2 = (KD)this.UT.get(var1);
      if (var2 == null) {
         Mh var3 = Mh.A;
         Object var4 = null;
         List var5 = null;
         IOException var6 = null;
         if (this.kS.getEntry(var1) != null) {
            try {
               ByteArrayInputStream var7 = new ByteArrayInputStream(this.kS.readEntry(var1));
               lO var8 = new lO(var7);
               WD var9 = var8.kS();
               var5 = var8.A();
               vi var10 = new vi(var9);
               if ("resources.arsc".equals(var1)) {
                  this.ys = var10.A();
                  this.ld = this.ys.E;
               }

               var3 = Mh.UT;
            } catch (IOException var11) {
               if (this.A()) {
                  throw new RuntimeException(var11);
               }

               var6 = var11;
               pC.catching(var11);
               var3 = Mh.kS;
            }
         }

         var2 = new KD(var3, var1, (TypedContent)var4, var5, var6);
         this.UT.put(var1, var2);
      }

      return var2;
   }

   public KD UT() {
      return this.pC("AndroidManifest.xml", true);
   }

   public KD pC(String var1, boolean var2) {
      KD var3 = (KD)this.UT.get(var1);
      if (var3 == null) {
         Mh var4 = Mh.A;
         TypedContent var5 = null;
         List var6 = null;
         Exception var7 = null;
         if (this.kS.getEntry(var1) != null) {
            try {
               ByteArrayInputStream var8 = new ByteArrayInputStream(this.kS.readEntry(var1));
               lO var9 = new lO(var8);
               hP var10 = var9.pC(this.sY, this.oT, var2, true);
               var6 = var9.A();
               String var11 = var10.wS();
               if (var1.equals("AndroidManifest.xml")) {
                  this.oT = var10.A();
               }

               var4 = var9.pC() ? Mh.UT : Mh.wS;
               var5 = TypedContent.bytes(Strings.encodeUTF8(var11));
            } catch (Exception var12) {
               if (this.A()) {
                  throw new RuntimeException(var12);
               }

               var7 = var12;
               pC.catching(var12);
               var4 = Mh.kS;
            }
         }

         var3 = new KD(var4, var1, var5, var6, var7);
         this.UT.put(var1, var3);
      }

      return var3;
   }

   public KD A(String var1) {
      KD var2 = (KD)this.UT.get(var1);
      if (var2 == null) {
         Mh var3 = Mh.A;
         TypedContent var4 = null;
         IOException var5 = null;
         if (this.kS.getEntry(var1) != null) {
            try {
               byte[] var6 = this.kS.readEntry(var1);
               var4 = TypedContent.bytes(var6);
               var3 = Mh.UT;
            } catch (IOException var7) {
               if (this.A()) {
                  throw new RuntimeException(var7);
               }

               var5 = var7;
               pC.catching(var7);
               var3 = Mh.kS;
            }
         }

         var2 = new KD(var3, var1, var4, null, var5);
         this.UT.put(var1, var2);
      }

      return var2;
   }

   public Collection pC(boolean var1, int var2) {
      Object[] var10000 = new Object[0];
      KD var3 = this.wS();
      boolean var4 = var3.sY();
      var10000 = new Object[0];
      this.UT();
      HashMap var5 = new HashMap();
      if (var4) {
         Tb var6 = new Tb(this.ys, this.sY, new bO(this, var5));
         var6.pC(var2);
         Map var7 = var6.pC();
         if (var7 != null) {
            this.UT.putAll(var7);
         }
      }

      Iterator var19 = this.kS.getEntries().values().iterator();

      while (true) {
         String var8;
         String var9;
         DB.Av var10;
         while (true) {
            if (!var19.hasNext()) {
               return this.UT.values();
            }

            GenericZipEntry var20 = (GenericZipEntry)var19.next();
            var8 = var20.getName();
            if (var8 != null && var8.length() != 0 && !var20.isDirectory()) {
               var9 = (String)var5.get(var8);
               var10000 = new Object[]{var8, Strings.safe(var9, var8)};

               try (InputStream var11 = this.kS.getEntryStream(var8)) {
                  var10 = DB.pC(var11, var8);
                  break;
               } catch (IOException var16) {
                  pC.catchingSilent(var16);
               } catch (Exception var17) {
               }
            }
         }

         KD var21;
         try {
            if (var10 == DB.Av.A) {
               var21 = this.pC(var8);
            } else if (var10 == DB.Av.kS) {
               var21 = this.pC(var8, true);
            } else {
               var21 = this.A(var8);
            }
         } catch (Exception var18) {
            pC.catchingSilent(var18);
            pC.warn(S.L("Parsing of file that seemed to contain arsc data failed: %s"), var8);
            continue;
         }

         if (var21 != null && var21.sY()) {
            var21.pC(var9);
         }
      }
   }

   @Override
   public String pC(int var1, int var2) {
      return var1 != 0 && var1 != this.kS() ? null : this.ys.A.pC(var2);
   }

   @Override
   public boolean pC(int var1, int var2, String var3) {
      if (var1 != 0 && var1 != this.kS()) {
         return false;
      } else {
         this.ys.A.A(var2, var3);
         return true;
      }
   }

   @Override
   public p A(int var1) {
      if (this.ld == null) {
         KD var2 = this.wS();
         if (!var2.sY()) {
            return null;
         }
      }

      return (p)this.ld.get(var1);
   }

   @Override
   public String kS(int var1) {
      p var2 = this.A(var1);
      return var2 == null ? null : var2.A;
   }
}
