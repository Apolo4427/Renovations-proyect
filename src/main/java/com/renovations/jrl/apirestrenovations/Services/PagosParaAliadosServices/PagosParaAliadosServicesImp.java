package com.renovations.jrl.apirestrenovations.Services.PagosParaAliadosServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renovations.jrl.apirestrenovations.Entities.PagosParaAliados;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

public class PagosParaAliadosServicesImp implements PagosParaAliadosServices {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public List<PagosParaAliados> getAllPagosAliado(Long id) {
        Proyecto proyecto = proyectoRepository.findByProyectoId(id);
        return proyecto.getListaDePagosAliados();  
    }

    @Override
    public List<PagosParaAliados> registrarPagoDelAliado(PagosParaAliados pago, Long id) {
        Proyecto proyecto = proyectoRepository.findByProyectoId(id);
        List<PagosParaAliados> pagosAliados = proyecto.getListaDePagosAliados();
        pagosAliados.add(pago);
        proyecto.setListaDePagosAliados(pagosAliados);
        proyectoRepository.save(proyecto);
        return proyecto.getListaDePagosAliados();
    }

    @Override
    public List<PagosParaAliados> actualizarPagoAliado(PagosParaAliados pago, String numeroContrato, Long id) {
        
        Proyecto proyecto = proyectoRepository.findByProyectoId(id);
        List<PagosParaAliados> pagosParaAliadosList = proyecto.getListaDePagosAliados();
        for(int i=0;i<pagosParaAliadosList.size();i++){
            if(pagosParaAliadosList.get(i).getPagoParaAliadoId()==id){
                pago.setPagoParaAliadoId(id);
                pagosParaAliadosList.set(i, pago);
            }
        }
        proyecto.setListaDePagosAliados(pagosParaAliadosList);
        proyectoRepository.save(proyecto);
        return pagosParaAliadosList;
    }

}
