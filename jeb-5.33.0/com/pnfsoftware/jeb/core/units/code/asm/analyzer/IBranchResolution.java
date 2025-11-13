package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import java.util.List;

public interface IBranchResolution {
   boolean isEmpty();

   List getTargets();

   boolean isResolved();

   IBranchTarget getResolvedTarget();

   List getCandidates();
}
