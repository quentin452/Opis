package mcp.mobius.opis.data.profilers;

import mcp.mobius.mobiuscore.profiler_v2.IProfilerBase;

public abstract class ProfilerAbstract implements IProfilerBase {

	@Override
	public abstract void reset();

	@Override
	public void start() {}

	@Override
	public void stop() {}

	@Override
	public void start(Object key) {}

	@Override
	public void stop(Object key) {}

	@Override
	public void start(Object key1, Object key2) {}

	@Override
	public void stop(Object key1, Object key2) {}

}