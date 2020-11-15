package io.itrunner.heroes.slice;

import io.itrunner.heroes.ResourceTable;
import io.itrunner.heroes.data.Hero;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

public class ListItemProvider extends BaseItemProvider {
    private List<Hero> data;
    private AbilitySlice slice;

    ListItemProvider(AbilitySlice abilitySlice, List<Hero> data) {
        slice = abilitySlice;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return this.data.get(i);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public Component getComponent(int position, Component convertView, ComponentContainer parent) {
        Component component = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_list_item, null, false);
        if (!(component instanceof ComponentContainer)) {
            return null;
        }
        ComponentContainer rootLayout = (ComponentContainer) component;
        Text leftText = (Text) rootLayout.findComponentById(ResourceTable.Id_list_content);
        leftText.setText(data.get(position).getName());
        return component;
    }
}