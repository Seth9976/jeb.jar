package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;

public class jx implements IPluginFileEntry {
   private static final ILogger gO = GlobalLog.getLogger(jx.class);
   Nz q;
   boolean RF;
   File xK;
   long Dw;
   Class Uv;
   IPlugin oW;

   public jx(Nz var1, Class var2, IPlugin var3, boolean var4, File var5) {
      this.Uv = var2;
      this.oW = var3;
      this.RF = var4;
      this.xK = var5;
      this.q = var1;
      if (var5 != null) {
         this.Dw = var5.lastModified();
      }
   }

   public Nz q() {
      return this.q;
   }

   @Override
   public boolean isValidPlugin() {
      return this.Uv != null;
   }

   @Override
   public boolean isScriptPlugin() {
      return this.RF;
   }

   @Override
   public boolean isPythonPlugin() {
      return this.xK != null && this.xK.getName().endsWith(".py");
   }

   @Override
   public File getFile() {
      return this.xK;
   }

   @Override
   public Class getPluginClass() {
      return this.Uv;
   }

   @Override
   public IPlugin getPluginObject() {
      return this.getPluginObject(false);
   }

   @Override
   public IPlugin getPluginObject(boolean var1) {
      if (!this.isValidPlugin()) {
         return null;
      } else if (!var1) {
         return this.oW;
      } else {
         try {
            return this.q != null ? this.q.q(this.Uv) : (IPlugin)this.Uv.getDeclaredConstructor().newInstance();
         } catch (Exception var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   public boolean RF() {
      return this.xK != null && (!this.xK.exists() || this.xK.lastModified() != this.Dw);
   }

   @Override
   public String toString() {
      return !this.isValidPlugin() ? S.L("Invalid plugin entry") : this.Uv.getName();
   }
}
