-- Components Query Example
SELECT * 
FROM CPU


-- Components (Price Range) Query Example
SELECT * 
FROM CPU
WHERE CPU.Price >= 0 and CPU.Price <= 1000;


-- PC Build(s) Query Example
SELECT DISTINCT FullBuild.Username,
	   FullBuild.BuildName,
	   FullBuild.CPUName,
	   FullBuild.CoolerName,
	   FullBuild.PSUName,
	   FullBuild.MoboName,
	   FullBuild.CaseName,
	   FullBuild.OSName,
	   FullBuild.MouseName,
	   FullBuild.KeyboardName,
	   BuildGPUs.BuildGpus,
	   BuildMem.BuildMemory,
	   BuildSt.BuildStorage,
	   BuildMon.BuildMonitors,
	   (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price
	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice 
	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice 
	   + MonBuildPrice.MonitorsTotalPrice) AS TotalPrice
FROM (
	SELECT PC.Username,
		   PC.BuildName,
		   CPUName,
		   CoolerName,
		   PSUName,
		   MoboName,
		   CaseName,
		   OSName,
		   MouseName,
		   KeyboardName,
		   GPU.GPUName,
		   Mem.MemoryName,
		   St.StorageName,
		   Mon.MonitorName
	FROM PCBuild AS PC
	JOIN BuildGpu AS GPU
	ON PC.Username = GPU.Username and PC.BuildName = GPU.BuildName
	JOIN BuildMemory AS Mem
	ON PC.Username = Mem.Username and PC.BuildName = Mem.BuildName
	JOIN BuildStorage AS St
	ON PC.Username = St.Username and PC.BuildName = St.BuildName
	JOIN BuildMonitor AS Mon
	ON PC.Username = Mon.Username and PC.BuildName = Mon.BuildName
	WHERE PC.Username = 'tyd3') AS FullBuild
JOIN CPU
ON FullBuild.CPUName = CPU.CPUName
JOIN CPUCooler AS Cool
ON FullBuild.CoolerName = Cool.CoolerName
JOIN PowerSupply AS PSU
ON FullBuild.PSUName = PSU.PSUName
JOIN Motherboard AS Mobo
ON FullBuild.MoboName = Mobo.MoboName
JOIN PCCase AS PCase
ON FullBuild.CaseName = PCase.CaseName
JOIN OS
ON FullBuild.OSName = OS.OSName
JOIN Mouse AS M
ON FullBuild.MouseName = M.MouseName
JOIN Keyboard AS Kb
ON FullBuild.KeyboardName = Kb.KeyboardName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(GPUName, ', ') AS BuildGpus
	FROM BuildGpu
	GROUP BY UserName, BuildName) AS BuildGPUs
ON FullBuild.Username = BuildGPUs.Username and FullBuild.BuildName = BuildGPUs.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS GPUsTotalPrice
	FROM BuildGpu
	JOIN VideoCard
	ON BuildGpu.GPUName = VideoCard.GPUName
	GROUP BY UserName, BuildName) AS GPUBuildPrice
ON FullBuild.Username = GPUBuildPrice.Username and FullBuild.BuildName = GPUBuildPrice.BuildName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(MemoryName, ', ') AS BuildMemory
	FROM BuildMemory
	GROUP BY UserName, BuildName) AS BuildMem
ON FullBuild.Username = BuildMem.Username and FullBuild.BuildName = BuildMem.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS MemoryTotalPrice
	FROM BuildMemory
	JOIN Memory
	ON BuildMemory.MemoryName = Memory.MemoryName
	GROUP BY UserName, BuildName) AS MemBuildPrice
ON FullBuild.Username = MemBuildPrice.Username and FullBuild.BuildName = MemBuildPrice.BuildName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(StorageName, ', ') AS BuildStorage
	FROM BuildStorage
	GROUP BY UserName, BuildName) AS BuildSt
ON FullBuild.Username = BuildSt.Username and FullBuild.BuildName = BuildSt.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS StorageTotalPrice
	FROM BuildStorage
	JOIN Storage
	ON BuildStorage.StorageName = Storage.StorageName
	GROUP BY UserName, BuildName) AS StBuildPrice
ON FullBuild.Username = StBuildPrice.Username and FullBuild.BuildName = StBuildPrice.BuildName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(MonitorName, ', ') AS BuildMonitors
	FROM BuildMonitor
	GROUP BY UserName, BuildName) AS BuildMon
ON FullBuild.Username = BuildMon.Username and FullBuild.BuildName = BuildMon.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS MonitorsTotalPrice
	FROM BuildMonitor
	JOIN Monitor
	ON BuildMonitor.MonitorName = Monitor.MonitorName
	GROUP BY UserName, BuildName) AS MonBuildPrice
ON FullBuild.Username = MonBuildPrice.Username and FullBuild.BuildName = MonBuildPrice.BuildName;


-- PC Build(s) (Price Range) Query Example
SELECT DISTINCT FullBuild.Username,
	   FullBuild.BuildName,
	   FullBuild.CPUName,
	   FullBuild.CoolerName,
	   FullBuild.PSUName,
	   FullBuild.MoboName,
	   FullBuild.CaseName,
	   FullBuild.OSName,
	   FullBuild.MouseName,
	   FullBuild.KeyboardName,
	   BuildGPUs.BuildGpus,
	   BuildMem.BuildMemory,
	   BuildSt.BuildStorage,
	   BuildMon.BuildMonitors,
	   (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price
	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice 
	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice 
	   + MonBuildPrice.MonitorsTotalPrice) AS TotalPrice
FROM (
	SELECT PC.Username,
		   PC.BuildName,
		   CPUName,
		   CoolerName,
		   PSUName,
		   MoboName,
		   CaseName,
		   OSName,
		   MouseName,
		   KeyboardName,
		   GPU.GPUName,
		   Mem.MemoryName,
		   St.StorageName,
		   Mon.MonitorName
	FROM PCBuild AS PC
	JOIN BuildGpu AS GPU
	ON PC.Username = GPU.Username and PC.BuildName = GPU.BuildName
	JOIN BuildMemory AS Mem
	ON PC.Username = Mem.Username and PC.BuildName = Mem.BuildName
	JOIN BuildStorage AS St
	ON PC.Username = St.Username and PC.BuildName = St.BuildName
	JOIN BuildMonitor AS Mon
	ON PC.Username = Mon.Username and PC.BuildName = Mon.BuildName) AS FullBuild
JOIN CPU
ON FullBuild.CPUName = CPU.CPUName
JOIN CPUCooler AS Cool
ON FullBuild.CoolerName = Cool.CoolerName
JOIN PowerSupply AS PSU
ON FullBuild.PSUName = PSU.PSUName
JOIN Motherboard AS Mobo
ON FullBuild.MoboName = Mobo.MoboName
JOIN PCCase AS PCase
ON FullBuild.CaseName = PCase.CaseName
JOIN OS
ON FullBuild.OSName = OS.OSName
JOIN Mouse AS M
ON FullBuild.MouseName = M.MouseName
JOIN Keyboard AS Kb
ON FullBuild.KeyboardName = Kb.KeyboardName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(GPUName, ', ') AS BuildGpus
	FROM BuildGpu
	GROUP BY UserName, BuildName) AS BuildGPUs
ON FullBuild.Username = BuildGPUs.Username and FullBuild.BuildName = BuildGPUs.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS GPUsTotalPrice
	FROM BuildGpu
	JOIN VideoCard
	ON BuildGpu.GPUName = VideoCard.GPUName
	GROUP BY UserName, BuildName) AS GPUBuildPrice
ON FullBuild.Username = GPUBuildPrice.Username and FullBuild.BuildName = GPUBuildPrice.BuildName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(MemoryName, ', ') AS BuildMemory
	FROM BuildMemory
	GROUP BY UserName, BuildName) AS BuildMem
ON FullBuild.Username = BuildMem.Username and FullBuild.BuildName = BuildMem.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS MemoryTotalPrice
	FROM BuildMemory
	JOIN Memory
	ON BuildMemory.MemoryName = Memory.MemoryName
	GROUP BY UserName, BuildName) AS MemBuildPrice
ON FullBuild.Username = MemBuildPrice.Username and FullBuild.BuildName = MemBuildPrice.BuildName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(StorageName, ', ') AS BuildStorage
	FROM BuildStorage
	GROUP BY UserName, BuildName) AS BuildSt
ON FullBuild.Username = BuildSt.Username and FullBuild.BuildName = BuildSt.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS StorageTotalPrice
	FROM BuildStorage
	JOIN Storage
	ON BuildStorage.StorageName = Storage.StorageName
	GROUP BY UserName, BuildName) AS StBuildPrice
ON FullBuild.Username = StBuildPrice.Username and FullBuild.BuildName = StBuildPrice.BuildName
JOIN (
	SELECT UserName, BuildName, STRING_AGG(MonitorName, ', ') AS BuildMonitors
	FROM BuildMonitor
	GROUP BY UserName, BuildName) AS BuildMon
ON FullBuild.Username = BuildMon.Username and FullBuild.BuildName = BuildMon.BuildName
JOIN (
	SELECT UserName, BuildName, SUM(Price) AS MonitorsTotalPrice
	FROM BuildMonitor
	JOIN Monitor
	ON BuildMonitor.MonitorName = Monitor.MonitorName
	GROUP BY UserName, BuildName) AS MonBuildPrice
ON FullBuild.Username = MonBuildPrice.Username and FullBuild.BuildName = MonBuildPrice.BuildName
WHERE (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price
	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice 
	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice 
	   + MonBuildPrice.MonitorsTotalPrice) >= 0
	   and (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price
	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice 
	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice 
	   + MonBuildPrice.MonitorsTotalPrice) <= 2000;


--Compatible Motherboards Query Example
SELECT Mobo.*
FROM CPU
JOIN Motherboard AS Mobo
ON CPU.Socket = Mobo.Socket
WHERE CPU.CPUName = 'AMD Ryzen 5 3600';


--Compatible CPUs Query Example
SELECT CPU.*
FROM Motherboard AS Mobo
JOIN CPU
ON Mobo.Socket = CPU.Socket 
WHERE Mobo.MoboName = 'Asus ROG Strix X570-I Gaming';