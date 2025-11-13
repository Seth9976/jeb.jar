package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipFailSafeReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public abstract class cvq {
   private IInput q;

   public cvq(IInput var1) {
      this.q = var1;
   }

   public boolean xK() throws IOException {
      if (this.q instanceof FileInput) {
         try {
            boolean var24;
            try (ZipFile var17 = new ZipFile(((FileInput)this.q).getFile())) {
               Enumeration var20 = var17.entries();

               while (var20.hasMoreElements()) {
                  ZipEntry var23 = (ZipEntry)var20.nextElement();
                  Boolean var26 = this.q(var23.getName());
                  if (var26 != null) {
                     return var26;
                  }
               }

               var24 = this.RF();
            }

            return var24;
         } catch (IOException var15) {
         }
      }

      try {
         boolean var19;
         try (ZipFailSafeReader var16 = new ZipFailSafeReader(this.q.getChannel(), -1, false, false, true)) {
            this.q();

            for (com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipEntry var22 : var16.enumerateEntries()) {
               Boolean var25 = this.q(var22.getFilenameUTF8());
               if (var25 != null) {
                  return var25;
               }
            }

            var19 = this.RF();
         }

         return var19;
      } catch (IOException var13) {
         IOException var1 = var13;
         if (!(this.q instanceof FileInput)) {
            try {
               boolean var21;
               try (ZipInputStream var2 = new ZipInputStream(this.q.getStream())) {
                  this.q();

                  ZipEntry var3;
                  while ((var3 = var2.getNextEntry()) != null) {
                     Boolean var4 = this.q(var3.getName());
                     if (var4 != null) {
                        return var4;
                     }
                  }

                  var21 = this.RF();
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

   protected abstract void q();

   protected abstract Boolean q(String var1);

   protected abstract boolean RF();

   public static class eo extends cvq {
      Set q;
      Set RF;
      Set xK = new HashSet();

      public eo(IInput var1, String... var2) {
         this(var1, new HashSet(Arrays.asList(var2)));
      }

      public eo(IInput var1, Set var2) {
         this(var1, var2, null);
      }

      public eo(IInput var1, Set var2, Set var3) {
         super(var1);
         this.q = var2 != null ? Collections.unmodifiableSet(var2) : Collections.emptySet();
         this.RF = var3 != null ? Collections.unmodifiableSet(var3) : Collections.emptySet();
      }

      @Override
      protected void q() {
         this.xK.clear();
      }

      @Override
      protected Boolean q(String var1) {
         if (this.q.contains(var1) && this.xK.add(var1) && this.xK.size() == this.q.size() && this.RF.isEmpty()) {
            return true;
         } else {
            return this.RF.contains(var1) ? false : null;
         }
      }

      @Override
      protected boolean RF() {
         return this.xK.size() == this.q.size();
      }
   }
}
