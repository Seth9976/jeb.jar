package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import java.util.List;

public class SimulationPointInformation {
   public CFG cfg;
   public BasicBlock bb;
   public int instructionIndex;
   public EState state;
   public List blkPreExecRegVals;
   public List blkPostExecRegVals;
   public boolean instructionEmulationFailed;
}
