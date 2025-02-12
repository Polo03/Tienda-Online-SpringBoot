package org.example.tienda_online.Service;

import org.example.tienda_online.Dto.Cliente;
import org.example.tienda_online.Dto.MoldeLogin;
import org.example.tienda_online.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    // Obtener cliente by ID
    public Optional<Cliente> obtenerClienteByID(Integer id) {
        return clienteRepository.findById(id);
    }

    //Guardar cliente
    public Cliente guardarCliente(Cliente usuario) {
        Cliente usuarioGuardar = clienteRepository.save(usuario);
        return usuarioGuardar;
    }

    //Actualizar cliente
    public boolean actualizarCliente(Cliente nuevoUsuario) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(nuevoUsuario.getId());
        if (clienteExistente.isPresent()) {
            clienteRepository.save(nuevoUsuario);
            return true;
        }
        return false;
    }

    //Eliminar cliente
    public boolean eliminarCliente(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean validarLogin(MoldeLogin moldeLogin){
        Optional<Cliente> cliente = clienteRepository.findByNicknameAndPassword(moldeLogin.getNickname(), moldeLogin.getPassword());
        if(cliente.isPresent()){
            return true;
        }
        return false;
    }

}
