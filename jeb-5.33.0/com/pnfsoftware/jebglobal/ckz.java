package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipFailSafeReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public abstract class ckz {
   private IInput pC;

   public ckz(IInput var1) {
      this.pC = var1;
   }

   public boolean kS() throws IOException {
      if (this.pC instanceof FileInput) {
         try {
            boolean var24;
            try (ZipFile var17 = new ZipFile(((FileInput)this.pC).getFile())) {
               Enumeration var20 = var17.entries();

               while (var20.hasMoreElements()) {
                  ZipEntry var23 = (ZipEntry)var20.nextElement();
                  Boolean var26 = this.pC(var23.getName());
                  if (var26 != null) {
                     return var26;
                  }
               }

               var24 = this.A();
            }

            return var24;
         } catch (IOException var15) {
         }
      }

      try {
         boolean var19;
         try (ZipFailSafeReader var16 = new ZipFailSafeReader(this.pC.getChannel(), -1, false, false, true)) {
            this.pC();

            for (com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipEntry var22 : var16.enumerateEntries()) {
               Boolean var25 = this.pC(var22.getFilenameUTF8());
               if (var25 != null) {
                  return var25;
               }
            }

            var19 = this.A();
         }

         return var19;
      } catch (IOException var13) {
         IOException var1 = var13;
         if (!(this.pC instanceof FileInput)) {
            try {
               boolean var21;
               try (ZipInputStream var2 = new ZipInputStream(this.pC.getStream())) {
                  this.pC();

                  ZipEntry var3;
                  while ((var3 = var2.getNextEntry()) != null) {
                     Boolean var4 = this.pC(var3.getName());
                     if (var4 != null) {
                        return var4;
                     }
                  }

                  var21 = this.A();
               }

               return var21;
            } catch (IOException var11) {
               var1 = var11;
            }
         }

         Assert.a(var1 != null);
         throw var1;
      }
   }

   protected abstract void pC();

   protected abstract Boolean pC(String var1);

   protected abstract boolean A();

   public static class Av extends ckz {
      Set pC;
      Set A;
      Set kS = new HashSet();

      public Av(IInput var1, Set var2, Set var3) {
         super(var1);
         this.pC = var2 != null ? Collections.unmodifiableSet(var2) : Collections.emptySet();
         this.A = var3 != null ? Collections.unmodifiableSet(var3) : Collections.emptySet();
      }

      @Override
      protected void pC() {
         this.kS.clear();
      }

      @Override
      protected Boolean pC(String var1) {
         if (this.pC.contains(var1) && this.kS.add(var1) && this.kS.size() == this.pC.size() && this.A.isEmpty()) {
            return true;
         } else {
            return this.A.contains(var1) ? false : null;
         }
      }

      @Override
      protected boolean A() {
         return this.kS.size() == this.pC.size();
      }
   }
}
