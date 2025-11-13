package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface UnicornLibrary extends Library {
   UnicornLibrary INSTANCE = (UnicornLibrary)Native.load("unicorn", UnicornLibrary.class);

   int uc_open(int var1, int var2, PointerByReference var3);

   int uc_close(Pointer var1);

   int uc_ctl(Pointer var1, int var2, Object... var3);

   String uc_strerror(int var1);

   int uc_errno(Pointer var1);

   int uc_mem_map(Pointer var1, long var2, long var4, int var6);

   int uc_mem_write(Pointer var1, long var2, byte[] var4, long var5);

   int uc_mem_read(Pointer var1, long var2, byte[] var4, long var5);

   int uc_emu_start(Pointer var1, long var2, long var4, long var6, long var8);

   int uc_emu_stop(Pointer var1);

   int uc_reg_write(Pointer var1, int var2, byte[] var3);

   int uc_reg_read(Pointer var1, int var2, byte[] var3);

   int uc_hook_add(Pointer var1, PointerByReference var2, int var3, Callback var4, Pointer var5, long var6, long var8, Object... var10);

   int uc_hook_del(Pointer var1, Pointer var2);

   public interface CodeHook extends Callback {
      void callback(Pointer var1, long var2, int var4, Pointer var5);
   }

   public interface MemoryHook extends Callback {
      int callback(Pointer var1, int var2, long var3, int var5, long var6, Pointer var8);
   }
}
