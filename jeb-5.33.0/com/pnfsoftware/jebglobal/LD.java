package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import java.util.Map;

public interface LD extends IRegisterData {
   Ti pC();

   byte[] A();

   Map pC(boolean var1);
}
