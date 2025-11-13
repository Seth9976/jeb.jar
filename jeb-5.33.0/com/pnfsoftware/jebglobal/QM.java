package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;

public class QM implements IPluginFileEntry {
   private static final ILogger sY = GlobalLog.getLogger(QM.class);
   jY pC;
   boolean A;
   File kS;
   long wS;
   Class UT;
   IPlugin E;

   public QM(jY var1, Class var2, IPlugin var3, boolean var4, File var5) {
      this.UT = var2;
      this.E = var3;
      this.A = var4;
      this.kS = var5;
      this.pC = var1;
      if (var5 != null) {
         this.wS = var5.lastModified();
      }
   }

   @Override
   public boolean isValidPlugin() {
      return this.UT != null;
   }

   @Override
   public boolean isScriptPlugin() {
      return this.A;
   }

   @Override
   public boolean isPythonPlugin() {
      return this.kS != null && this.kS.getName().endsWith(".py");
   }

   @Override
   public File getFile() {
      return this.kS;
   }

   @Override
   public Class getPluginClass() {
      return this.UT;
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
         return this.E;
      } else {
         try {
            return this.pC != null ? this.pC.pC(this.UT) : (IPlugin)this.UT.getDeclaredConstructor().newInstance();
         } catch (Exception var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   public boolean pC() {
      return this.kS != null && (!this.kS.exists() || this.kS.lastModified() != this.wS);
   }

   @Override
   public String toString() {
      return !this.isValidPlugin() ? S.L("Invalid plugin entry") : this.UT.getName();
   }
}
