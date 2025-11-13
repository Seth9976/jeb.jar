package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.encoding.zip.GenericZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.IGenericZipBrowser;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.IO;
import com.pnfsoftware.jebglobal.Kd;
import com.pnfsoftware.jebglobal.kO;
import com.pnfsoftware.jebglobal.nH;
import com.pnfsoftware.jebglobal.oe;
import com.pnfsoftware.jebglobal.pK;
import com.pnfsoftware.jebglobal.pN;
import com.pnfsoftware.jebglobal.ta;
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

public class ej implements PY {
   private static final ILogger xK = GlobalLog.getLogger(ej.class);
   public static final String q = "resources.arsc";
   public static final String RF = "AndroidManifest.xml";
   private File Dw;
   private IGenericZipBrowser Uv;
   private Map oW;
   private Map gO = new HashMap();
   private CU nf;
   private kY gP;
   private Nz za;
   private Map lm;
   private Boolean zz;
   private String JY;

   public ej(File var1, CU var2) throws IOException {
      this.Dw = var1;
      this.Uv = CU.q(this.Dw);
      this.nf = var2;
      this.gP = new kY((PY)(var2 != null ? var2 : this));
   }

   public File q() {
      return this.Dw;
   }

   public void q(Boolean var1) {
      this.zz = var1;
   }

   public boolean RF() {
      if (this.zz == null) {
         return this.nf != null ? this.nf.q() : false;
      } else {
         return this.zz;
      }
   }

   public int xK() {
      this.q(0);
      if (this.oW.isEmpty()) {
         throw new RuntimeException("Arsc package map is empty!");
      } else {
         return (Integer)this.oW.keySet().iterator().next();
      }
   }

   public synchronized boolean q(int var1) {
      if (this.oW == null) {
         this.oW = Collections.emptyMap();
         if (this.Uv.getEntry("resources.arsc") != null) {
            try (InputStream var2 = this.Uv.getEntryStream("resources.arsc")) {
               this.oW = nH.q(var2);
            } catch (IOException var7) {
               if (this.RF()) {
                  throw new RuntimeException(var7);
               }

               xK.catching(var7);
               JebCoreService.notifySilentExceptionToClient(new nI("Failed parsing arsc header for apk " + this.Dw.getName(), var7));
            }
         }
      }

      return this.oW.containsKey(var1);
   }

   public String q(boolean var1) throws nI {
      if (!this.Dw().gO()) {
         throw new nI("resources.arsc was not successfully parsed");
      } else {
         return new vb(this.za, var1).q();
      }
   }

   public qV Dw() {
      return this.q("resources.arsc");
   }

   public qV q(String var1) {
      qV var2 = (qV)this.gO.get(var1);
      if (var2 == null) {
         Uz var3 = Uz.RF;
         Object var4 = null;
         List var5 = null;
         IOException var6 = null;
         if (this.Uv.getEntry(var1) != null) {
            try {
               ByteArrayInputStream var7 = new ByteArrayInputStream(this.Uv.readEntry(var1));
               kO var8 = new kO(var7);
               Kd var9 = var8.Dw();
               var5 = var8.RF();
               bK var10 = new bK(var9);
               if ("resources.arsc".equals(var1)) {
                  this.za = var10.xK();
                  this.lm = this.za.oW;
               }

               var3 = Uz.Uv;
            } catch (IOException var11) {
               if (this.RF()) {
                  throw new RuntimeException(var11);
               }

               var6 = var11;
               xK.catching(var11);
               var3 = Uz.xK;
            }
         }

         var2 = new qV(var3, var1, (TypedContent)var4, var5, var6);
         this.gO.put(var1, var2);
      }

      return var2;
   }

   public qV Uv() {
      return this.q("AndroidManifest.xml", true);
   }

   public qV q(String var1, boolean var2) {
      qV var3 = (qV)this.gO.get(var1);
      if (var3 == null) {
         Uz var4 = Uz.RF;
         TypedContent var5 = null;
         List var6 = null;
         Exception var7 = null;
         if (this.Uv.getEntry(var1) != null) {
            try {
               ByteArrayInputStream var8 = new ByteArrayInputStream(this.Uv.readEntry(var1));
               kO var9 = new kO(var8);
               IO var10 = var9.q(this.gP, this.JY, var2, true);
               var6 = var9.RF();
               String var11 = var10.Dw();
               if (var1.equals("AndroidManifest.xml")) {
                  this.JY = var10.RF();
               }

               var4 = var9.q() ? Uz.Uv : Uz.Dw;
               var5 = TypedContent.bytes(Strings.encodeUTF8(var11));
            } catch (Exception var12) {
               if (this.RF()) {
                  throw new RuntimeException(var12);
               }

               var7 = var12;
               xK.catching(var12);
               var4 = Uz.xK;
            }
         }

         var3 = new qV(var4, var1, var5, var6, var7);
         this.gO.put(var1, var3);
      }

      return var3;
   }

   public qV RF(String var1) {
      qV var2 = (qV)this.gO.get(var1);
      if (var2 == null) {
         Uz var3 = Uz.RF;
         TypedContent var4 = null;
         IOException var5 = null;
         if (this.Uv.getEntry(var1) != null) {
            try {
               byte[] var6 = this.Uv.readEntry(var1);
               var4 = TypedContent.bytes(var6);
               var3 = Uz.Uv;
            } catch (IOException var7) {
               if (this.RF()) {
                  throw new RuntimeException(var7);
               }

               var5 = var7;
               xK.catching(var7);
               var3 = Uz.xK;
            }
         }

         var2 = new qV(var3, var1, var4, null, var5);
         this.gO.put(var1, var2);
      }

      return var2;
   }

   public Collection q(boolean var1, int var2) {
      Object[] var10000 = new Object[0];
      qV var3 = this.Dw();
      boolean var4 = var3.gO();
      var10000 = new Object[0];
      this.Uv();
      HashMap var5 = new HashMap();
      if (var4) {
         Xa var6 = new Xa(this.za, this.gP, new oM(this, var5));
         var6.q(var2);
         Map var7 = var6.RF();
         if (var7 != null) {
            this.gO.putAll(var7);
         }
      }

      Iterator var19 = this.Uv.getEntries().values().iterator();

      while (true) {
         String var8;
         String var9;
         nH.eo var10;
         while (true) {
            if (!var19.hasNext()) {
               return this.gO.values();
            }

            GenericZipEntry var20 = (GenericZipEntry)var19.next();
            var8 = var20.getName();
            if (var8 != null && var8.length() != 0 && !var20.isDirectory()) {
               var9 = (String)var5.get(var8);
               var10000 = new Object[]{var8, Strings.safe(var9, var8)};

               try (InputStream var11 = this.Uv.getEntryStream(var8)) {
                  var10 = nH.q(var11, var8);
                  break;
               } catch (IOException var16) {
                  xK.catchingSilent(var16);
               } catch (Exception var17) {
               }
            }
         }

         qV var21;
         try {
            if (var10 == nH.eo.RF) {
               var21 = this.q(var8);
            } else if (var10 == nH.eo.xK) {
               var21 = this.q(var8, true);
            } else {
               var21 = this.RF(var8);
            }
         } catch (Exception var18) {
            xK.catchingSilent(var18);
            xK.warn(S.L("Parsing of file that seemed to contain arsc data failed: %s"), var8);
            continue;
         }

         if (var21 != null && var21.gO()) {
            var21.q(var9);
         }
      }
   }

   void oW() {
   }

   @Override
   public String q(int var1, int var2) {
      return var1 != 0 && var1 != this.xK() ? null : this.za.RF.q(var2);
   }

   @Override
   public boolean q(int var1, int var2, String var3) {
      if (var1 != 0 && var1 != this.xK()) {
         return false;
      } else {
         this.za.RF.RF(var2, var3);
         return true;
      }
   }

   @Override
   public tl RF(int var1) {
      if (this.lm == null) {
         qV var2 = this.Dw();
         if (!var2.gO()) {
            return null;
         }
      }

      return (tl)this.lm.get(var1);
   }

   @Override
   public String xK(int var1) {
      tl var2 = this.RF(var1);
      return var2 == null ? null : var2.RF;
   }

   @Override
   public String q(int var1, String var2) {
      if (this.lm == null) {
         qV var3 = this.Dw();
         if (!var3.gO()) {
            return null;
         }
      }

      tl var5 = (tl)this.lm.get(var1);
      if (var5 == null) {
         return null;
      } else {
         ta var4 = (ta)var5.oW.get(var2);
         return var4 == null ? null : this.gP.q(var4);
      }
   }

   public byte[] q(Integer var1, boolean var2) throws IOException {
      this.Dw();
      ByteArrayInputStream var3 = new ByteArrayInputStream(this.Uv.readEntry("AndroidManifest.xml"));
      pN var4 = new pN(new oe(var3), this.gP, this.JY, true, true);
      int var5 = 0;
      int var6 = var4.q("application", "debuggable", true, 16842767, var1);
      if (var6 < 0) {
         throw new IOException();
      } else {
         if (var6 != 1) {
            var5++;
         }

         if (var2) {
            var6 = var4.q("application", "extractNativeLibs", true, 16844010, null);
            if (var6 < 0) {
               throw new IOException();
            }

            if (var6 != 1) {
               var5++;
            }
         }

         if (var5 == 0) {
            return null;
         } else {
            pK var7 = new pK();
            var4.q(var7);
            return var7.RF();
         }
      }
   }
}
